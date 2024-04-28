package com.etu.api.repositories;

import com.etu.api.entities.Grade;
import com.etu.api.entities.Task;
import com.etu.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query("SELECT g FROM Grade g WHERE g.user = :user AND g.task = :task")
    Grade findByUserAndTask(User user, Task task);

    List<Grade> findAllByUser(User user);
}
