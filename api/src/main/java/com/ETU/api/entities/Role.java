package com.ETU.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Ilya Tsygankov
 * @created 17.08.2023
 */
@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "role_id")
    private Integer role_id;

    @Column(name = "name")
    private String name;

    public Role() {}

    public Role(Integer role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }
}
