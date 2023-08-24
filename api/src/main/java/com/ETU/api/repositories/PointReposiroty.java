package com.ETU.api.repositories;

import com.ETU.api.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointReposiroty extends JpaRepository<Point, Integer> {
    @Query("SELECT p FROM Point p WHERE p.task.id = :task_id")
    List<Point> findByTaskId(Integer task_id);
}
