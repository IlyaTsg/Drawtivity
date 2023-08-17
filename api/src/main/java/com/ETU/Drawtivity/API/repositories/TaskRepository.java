package com.ETU.DemoApi.repositories;

import com.ETU.DemoApi.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
