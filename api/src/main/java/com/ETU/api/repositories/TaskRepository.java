package com.ETU.api.repositories;

import com.ETU.api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ilya Tsygankov
 * @created 17.08.2023
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
