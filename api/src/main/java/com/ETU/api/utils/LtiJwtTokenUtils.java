package com.etu.api.utils;

import com.etu.api.dtos.LtiLaunchRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LtiJwtTokenUtils {
    @Value("${lti.jwt.secret}")
    private String secret;
    @Value("${lti.jwt.lifetime}")
    private Duration jwtLifetime;
    @Value("${lti.jwt.iss}")
    private String issuer;
    @Value("${lti.jwt.sub}")
    private String subject;
    @Value("${lti.jwt.aud}")
    private String audience;
    @Value("${lti.jwt.jti}")
    private String token_id;
    @Value("${lti.client_id}")
    private String client_id;
    @Value("${lti.redirect_uri}")
    private String redirect_uri;
    @Value("${lti.scope}")
    private String scope;

    public String generateLtiJwtToken(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("client_id", client_id);
        claims.put("redirect_uri", redirect_uri);
        claims.put("scope", scope);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setSubject(subject)
                .setAudience(audience)
                .setId(token_id)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateLtiUserJwtToken(LtiLaunchRequest ltiLaunchRequest){
        Map<String, Object> claims = new HashMap<>();
        claims.put("target_link_uri", ltiLaunchRequest.getTarget_link_uri());
        claims.put("deployment_id", ltiLaunchRequest.getDeployment_id());
        claims.put("context", ltiLaunchRequest.getContext());
        claims.put("tool_consumer_instance_guid", ltiLaunchRequest.getTool_consumer_instance_guid());

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());

        return Jwts.builder()
                .setSubject(ltiLaunchRequest.getLogin_hint())
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
