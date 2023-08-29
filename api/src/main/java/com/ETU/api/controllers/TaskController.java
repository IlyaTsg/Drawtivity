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
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping(value="/{id}")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> getTaskById(@PathVariable("id") int id){
        return taskService.loadTaskById(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto createTaskDto){
        return taskService.createTask(createTaskDto);
    }

    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> updateTaskById(@PathVariable("id") Integer id, @RequestBody CreateTaskDto createTaskDto){
        return taskService.updateTaskById(id, createTaskDto);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Integer id){
        return taskService.deleteTaskById(id);
    }

    @PostMapping("/solution")
    @CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization", "Origin"},
            exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> solutionTask(@RequestBody SolutionRequest solutionRequest){
        return taskService.solutionTask(solutionRequest);
    }
}
