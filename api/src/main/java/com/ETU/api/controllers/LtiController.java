package com.ETU.api.controllers;

import com.ETU.api.dtos.LtiLaunchRequest;
import com.ETU.api.dtos.LtiRegistrationResponse;
import com.ETU.api.dtos.SolutionRequest;
import com.ETU.api.service.LtiService;
import com.ETU.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/lti")
public class LtiController {
    private final LtiService ltiService;
    private final TaskService taskService;
    @Autowired
    public LtiController(LtiService ltiService, TaskService taskService) {
        this.ltiService = ltiService;
        this.taskService = taskService;
    }

    @PostMapping("/registration")
    public ResponseEntity<LtiRegistrationResponse> registration(){
        return ltiService.createLtiRegistration();
    }
    
    @PostMapping("/launch")
    public RedirectView launch(@RequestBody LtiLaunchRequest ltiLaunchRequest){
        // Построение JWT токена для Moodle пользователя для дальнейшей проверки и пересылки оценок
        // Добавить после реализации проверки токена
        //String ltiUserToken = ltiService.createLtiUserJwtToken(ltiLaunchRequest.getLogin_hint());

        String user = ltiLaunchRequest.getLogin_hint();

        // Перенавправление запроса на страницу задачи
        String externalUrl = "http://localhost:8080/test?user="+user;
        return new RedirectView(externalUrl);
    }

    @PostMapping("/solution")
    public ResponseEntity<?> solutionTask(@PathParam("user") String user, @RequestBody SolutionRequest solutionRequest){
        return taskService.solutionTask(solutionRequest);
    }
}
