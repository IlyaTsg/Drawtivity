package com.etu.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name="lti_user")
public class LtiUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "target_link_uri")
    private String target_link_uri;

    @Column(name = "deployment_id")
    private String deployment_id;

    @Column(name = "context")
    private String context;

    @Column(name = "tool_consumer_instance_guid")
    private String tool_consumer_instance_guid;

    public LtiUser(int id, String username, String target_link_uri, String deployment_id, String context, String tool_consumer_instance_guid) {
        this.id = id;
        this.username = username;
        this.target_link_uri = target_link_uri;
        this.deployment_id = deployment_id;
        this.context = context;
        this.tool_consumer_instance_guid = tool_consumer_instance_guid;
    }

    public LtiUser() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTarget_link_uri() {
        return target_link_uri;
    }

    public void setTarget_link_uri(String target_link_uri) {
        this.target_link_uri = target_link_uri;
    }

    public String getDeployment_id() {
        return deployment_id;
    }

    public void setDeployment_id(String deployment_id) {
        this.deployment_id = deployment_id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTool_consumer_instance_guid() {
        return tool_consumer_instance_guid;
    }

    public void setTool_consumer_instance_guid(String tool_consumer_instance_guid) {
        this.tool_consumer_instance_guid = tool_consumer_instance_guid;
    }
}
