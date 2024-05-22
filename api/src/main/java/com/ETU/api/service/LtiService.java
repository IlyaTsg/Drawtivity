package com.etu.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.imsglobal.lti2.objects.consumer.ToolConsumer;
import org.imsglobal.lti2.objects.provider.SecurityContract;
import org.imsglobal.lti2.objects.provider.ToolProfile;
import org.imsglobal.pox.IMSPOXRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LtiService {
    @Value("${lti.default_base_url}")
    private String default_base_url;
    @Value("${lti.secure_base_url}")
    private String secure_base_url;
    @Value("${lti.basic_lti_launch_path}")
    private String basic_lti_launch_path;
    @Value("${lti.secret}")
    private String secret;

    public void sendGrade(String outcome_service_url, String oauth_consumer_key, String lis_result_sourcedid, String score) {
        try {
            IMSPOXRequest.sendReplaceResult(outcome_service_url, oauth_consumer_key, secret, lis_result_sourcedid, score);
            System.out.println("Grade sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send grade");
        }
    }

    public ToolProfile getToolProfile() {
        ToolProfile toolProfile = new ToolProfile();
        toolProfile.setBase_url_choice(getBaseUrlChoices());
        toolProfile.setResource_handler(getResourceHandler());
        return toolProfile;
    }

    private ArrayNode getBaseUrlChoices() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode choices = mapper.createArrayNode();

        ObjectNode baseUrlChoice = mapper.createObjectNode();
        baseUrlChoice.put("default_base_url", default_base_url);
        baseUrlChoice.put("secure_base_url", secure_base_url);

        baseUrlChoice.put("selector", "DefaultSelector");

        choices.add(baseUrlChoice);
        return choices;
    }

    private ArrayNode getResourceHandler() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode resourceHandler = mapper.createArrayNode();

        ObjectNode resource = mapper.createObjectNode();

        ArrayNode messageList = mapper.createArrayNode();
        ObjectNode message = mapper.createObjectNode();
        message.put("message_type", ToolConsumer.LtiCapability.BASICLTI_LAUNCH);
        message.put("path", basic_lti_launch_path);
        ArrayNode parameterList = mapper.createArrayNode();
        ObjectNode parameter = mapper.createObjectNode();
        parameter.put("name", "test_course_task");
        parameter.put("variable", ToolConsumer.LtiCapability.CO_TITLE);
        parameterList.add(parameter);
        message.put("parameter", parameterList);
        messageList.add(message);

        resource.put("message", messageList);
        resource.put("name", getValue("Drawitivity LTI"));

        resourceHandler.add(resource);
        return resourceHandler;
    }

    private ObjectNode getValue(String value) {
        ObjectNode valueNode = new ObjectMapper().createObjectNode();
        valueNode.put("default_value", value);
        return valueNode;
    }

    public SecurityContract getSecurityContract() {
        SecurityContract contract = new SecurityContract();
        contract.setShared_secret(secret);
        return contract;
    }

    public String getSecret() {
        return secret;
    }
}
