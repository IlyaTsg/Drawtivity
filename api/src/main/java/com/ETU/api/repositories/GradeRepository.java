package com.ETU.api.repositories;

import com.ETU.api.entities.Grade;
import com.ETU.api.entities.Task;
import com.ETU.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ilya Tsygankov
 * @created 29.04.2024
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query("SELECT g FROM Grade g WHERE g.user = :user AND g.task = :task")
    Grade findByUserAndTask(User user, Task task);

    List<Grade> findAllByUser(User user);
}
