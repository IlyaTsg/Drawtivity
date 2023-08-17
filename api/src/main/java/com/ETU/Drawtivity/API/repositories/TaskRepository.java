package com.ETU.Drawtivity.API.repositories;

import com.ETU.Drawtivity.API.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
