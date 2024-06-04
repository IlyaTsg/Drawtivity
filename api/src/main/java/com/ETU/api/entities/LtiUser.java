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

    public LtiUser() {}

    public LtiUser(int id, String username, String target_link_uri, String deployment_id, String context, String tool_consumer_instance_guid) {
        this.id = id;
        this.username = username;
        this.target_link_uri = target_link_uri;
        this.deployment_id = deployment_id;
        this.context = context;
        this.tool_consumer_instance_guid = tool_consumer_instance_guid;
    }
}
