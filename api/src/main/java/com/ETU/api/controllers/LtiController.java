package com.ETU.api.controllers;

import com.ETU.api.dtos.LtiLaunchRequest;
import com.ETU.api.dtos.LtiRegistrationResponse;
import com.ETU.api.service.LtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/lti")
public class LtiController {
    private final LtiService ltiService;

    @Autowired
    public LtiController(LtiService ltiService) {
        this.ltiService = ltiService;
    }

    @PostMapping("/registration")
    public ResponseEntity<LtiRegistrationResponse> registration(){
        return ltiService.createLtiRegistration();
    }

    @PostMapping("/launch")
    public RedirectView launch(@RequestBody LtiLaunchRequest ltiLaunchRequest){
        // Обработка параметров
        String ltiUserToken = ltiService.createLtiUserJwtToken(ltiLaunchRequest.getLogin_hint());

        // Перенавправление запроса на страницу задачи
        String externalUrl = "http://localhost:8080/test?token="+ltiUserToken;
        return new RedirectView(externalUrl);
    }
}
