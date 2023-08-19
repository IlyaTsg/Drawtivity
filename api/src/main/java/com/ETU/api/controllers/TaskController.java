package com.ETU.api.controllers;

import com.ETU.api.dtos.CreateTaskDto;
import com.ETU.api.dtos.SolutionRequest;
import com.ETU.api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable("id") int id){
        return taskService.loadTaskById(id);
    }

    @PostMapping()
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto createTaskDto){
        return taskService.createTask(createTaskDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTaskById(@PathVariable("id") Integer id, @RequestBody CreateTaskDto createTaskDto){
        return taskService.updateTaskById(id, createTaskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Integer id){
        return taskService.deleteTaskById(id);
    }

    @PostMapping("/solution")
    public ResponseEntity<?> solutionTask(@RequestBody SolutionRequest solutionRequest){
        return taskService.solutionTask(solutionRequest);
    }
}
