package com.etu.api.controllers;

import com.etu.api.dtos.LtiLaunchRequest;
import com.etu.api.dtos.LtiRegistrationResponse;
import com.etu.api.dtos.SolutionRequest;
import com.etu.api.service.LtiService;
import com.etu.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.websocket.server.PathParam;

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
        return ltiService.launch(ltiLaunchRequest);
    }

    @PostMapping("/solution")
    public ResponseEntity<?> solutionTask(@PathParam("user") String user, @RequestBody SolutionRequest solutionRequest){
        return taskService.solutionTask(solutionRequest);
    }
}
