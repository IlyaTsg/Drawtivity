package com.etu.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grades")
@IdClass(GradeId.class)
public class Grade {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;

    @Column(name = "grade")
    private Float grade;

    public Grade() {}

    public Grade(User user, Task task, Float grade) {
        this.user = user;
        this.task = task;
        this.grade = grade;
    }
}
