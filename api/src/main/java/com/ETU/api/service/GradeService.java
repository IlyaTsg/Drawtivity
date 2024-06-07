package com.ETU.api.service;

import com.ETU.api.entities.Grade;
import com.ETU.api.entities.Task;
import com.ETU.api.entities.User;
import com.ETU.api.repositories.GradeRepository;
import com.ETU.api.repositories.TaskRepository;
import com.ETU.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade getGrade(User user, Task task) {
        return gradeRepository.findByUserAndTask(user, task);
    }

    public void addGrade(Grade grade) throws Exception {
        gradeRepository.save(grade);
    }

    public void deleteGrade(Grade grade) {
        gradeRepository.delete(grade);
    }

    public List<Grade> getGradesByUser(User user) {
        return gradeRepository.findAllByUser(user);
    }
}
