package com.ETU.DemoApi.controllers;

import com.ETU.DemoApi.dtos.CreateTaskDto;
import com.ETU.DemoApi.service.TaskService;
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

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto createTaskDto){
        return taskService.createTask(createTaskDto);
    }
}
