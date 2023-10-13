package com.etu.api.service;

import com.etu.api.dtos.LtiLaunchRequest;
import com.etu.api.dtos.LtiRegistrationResponse;
import com.etu.api.utils.LtiJwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class LtiService {
    private final LtiJwtTokenUtils ltiJwtTokenUtils;

    @Value("${lti.grant_type}")
    private String grant_type;
    @Value("${lti.client_assertion_type}")
    private String client_assertion_type;
    @Value("${lti.redirect_uri}")
    private String redirect_uri;
    @Value("${lti.state}")
    private String state;
    @Value("${lti.response_type}")
    private String response_type;
    @Value("${lti.scope}")
    private String scope;
    @Value("${lti.login_hint}")
    private String login_hint;

    @Autowired
    public LtiService(LtiJwtTokenUtils ltiJwtTokenUtils) {
        this.ltiJwtTokenUtils = ltiJwtTokenUtils;
    }

    public ResponseEntity<LtiRegistrationResponse> createLtiRegistration(){
        // Построение токена по которому Moodle определит, что запрос пришел от нужного Tool Provider
        String client_assertion = ltiJwtTokenUtils.generateLtiJwtToken();

        LtiRegistrationResponse ltiRegistrationResponse = new LtiRegistrationResponse(grant_type, client_assertion_type, client_assertion, redirect_uri, state, response_type, scope, login_hint);

        return ResponseEntity.ok(ltiRegistrationResponse);
    }

    public RedirectView launch(LtiLaunchRequest ltiLaunchRequest)
    {
        // Create new LTI user

        String ltiUserToken = ltiJwtTokenUtils.generateLtiUserJwtToken(ltiLaunchRequest);

        String user = ltiLaunchRequest.getLogin_hint();

        // Перенавправление запроса на страницу задачи
        String externalUrl = "http://localhost:80/api/test?user="+user;
        return new RedirectView(externalUrl);
    }
}
