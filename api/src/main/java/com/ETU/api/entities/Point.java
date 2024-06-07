package com.ETU.api.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Ilya Tsygankov
 * @created 17.08.2023
 */
@Entity
@Data
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @JsonIgnore
    private Integer point_id;

    private Float x,y;

    @ManyToOne
    @JoinColumn(name="task_id", referencedColumnName = "task_id")
    @JsonIgnore
    private Task task;

    public Point() {}

    public Point(Integer point_id, Float x, Float y) {
        this.point_id = point_id;
        this.x = x;
        this.y = y;
    }
}
