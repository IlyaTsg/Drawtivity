package com.ETU.api.controllers;

import com.ETU.api.dtos.lti.LtiSolutionRequest;
import com.ETU.api.dtos.lti.ToolRegRequest;
import com.ETU.api.entities.Task;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.repositories.TaskRepository;
import com.ETU.api.service.LtiService;
import com.ETU.api.utils.TaskUtils;
import com.ETU.api.utils.lti.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.imsglobal.aspect.Lti;
import org.imsglobal.lti.launch.*;
import org.imsglobal.lti2.objects.consumer.ToolConsumer;
import org.imsglobal.lti2.objects.provider.ToolProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Ilya Tsygankov
 * @created 26.08.2023
 */
@Tag(name = "lti-controller")
@RestController
@RequestMapping("/lti")
public class LtiController {
    private final LtiService ltiService;
    private final TaskRepository taskRepository;
    private final TaskUtils taskUtils;
    private final LtiSigner ltiSigner;

    @Autowired
    public LtiController(LtiService ltiService, TaskRepository taskRepository, TaskUtils taskUtils, LtiSigner ltiSigner) {
        this.ltiService = ltiService;
        this.taskRepository = taskRepository;
        this.taskUtils = taskUtils;
        this.ltiSigner = ltiSigner;
    }

    @GetMapping("/toolConsumerProfile")
    @Operation(summary = "Профиль LTI потребителя", description = "Профиль LTI потребителя")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = ToolConsumer.class)))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getToolCosumerProfile(@RequestParam String tc_profile_url) {
        try {
            ToolConsumer toolConsumer = JsonReader.readJsonFromUrl(tc_profile_url, ToolConsumer.class);
            return ResponseEntity.ok(toolConsumer);
        } catch (IOException e) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Profile not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/toolRegistration")
    @Operation(summary = "Регистрация инструмента у потребителя", description = "Регистрация инструмента у потребителя")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "Created tool proxy")))
    public ResponseEntity<?> toolRegistration(@RequestBody ToolRegRequest toolRegRequest) throws Exception {
        // Формируем инструмент
        ToolProxy tp = new ToolProxy();
        tp.setContext(ToolProxy.CONTEXT_URL);
        tp.setTool_proxy_guid(UUID.randomUUID().toString());
        tp.setId("drawtivity-tool");
        tp.setType("ToolProxy");
        tp.setTool_profile(ltiService.getToolProfile());
        tp.setSecurity_contract(ltiService.getSecurityContract());

        ObjectMapper mapper = new ObjectMapper();
        HttpPost request = new HttpPost(toolRegRequest.getEndpoint());
        request.setHeader("Content-type", "application/json");
        request.setEntity(new StringEntity(mapper.writeValueAsString(tp)));

        ltiSigner.sign(request, toolRegRequest.getReg_key(), toolRegRequest.getReg_password());

        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() >= 400) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(response.getEntity().getContent(), writer, "utf-8");
            String theString = writer.toString();
            return new ResponseEntity<>(new ErrorDto(response.getStatusLine().getStatusCode(), "Error from tool consumer!"), HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
        }

        return ResponseEntity.ok("Created tool proxy");
    }

    @Lti
    @PostMapping("/launchVerification")
    @Operation(summary = "Верификация launch запроса от потребителя", description = "Верификация launch запроса от потребителя")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "Lti veirification success!")))
    @ApiResponse(responseCode = "403", content = @Content(
            schema = @Schema(example = "Lti verification failed!")))
    public ResponseEntity<?> launchVerification(@RequestBody JsonNode request) throws LtiVerificationException {
        LtiVerifier ltiVerifier = new LtiOauthVerifier();

        // Переводим все парметры запроса в Map для верификации
        Map<String, String> params = new HashMap<>();
        Iterator<String> parameterNames = request.fieldNames();
        for (Iterator<String> it = parameterNames; it.hasNext(); ) {
            String paramName = it.next();
            String paramValue = request.get(paramName).textValue();
            if (paramValue != null) {
                params.put(paramName, paramValue.trim());
            }
        }

        LtiVerificationResult result = ltiVerifier.verifyParameters(params, "http://localhost:4000/send-message", "POST", ltiService.getSecret());
        System.out.println("LTI launch message: " + result.getMessage());
        System.out.println("LTI error message: " + result.getError());
        System.out.println("LTI result message: " + result.getLtiLaunchResult());

        if (!result.getSuccess()) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.FORBIDDEN.value(), "Lti verification failed!"), HttpStatus.FORBIDDEN);
        } else {
            return ResponseEntity.ok("Lti veirification success!");
        }
    }

    @PostMapping("/taskSolution")
    @Operation(summary = "Проверка решения задачи", description = "Проверка решения задачи")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "75")))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> taskSolution(@RequestBody LtiSolutionRequest ltiSolutionRequest) {
        Task task = taskRepository.findById(ltiSolutionRequest.getTask_id()).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }

        double result = 0D;
        if (Objects.equals(task.getType(), "Linear")) {
            result = taskUtils.linearTaskSolution(task, ltiSolutionRequest.getPoints());
        } else if (Objects.equals(task.getType(), "Overlap")) {
            result = taskUtils.overlapTaskSolution(task, ltiSolutionRequest.getPoints());
        }

        ltiService.sendGrade(ltiSolutionRequest.getOutcome_service_url(), ltiSolutionRequest.getOauth_consumer_key(), ltiSolutionRequest.getLis_result_sourcedid(), Double.toString(result / 100));

        return ResponseEntity.ok(result);
    }
}
