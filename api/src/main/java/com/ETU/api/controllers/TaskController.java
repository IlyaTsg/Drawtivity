package com.etu.api.controllers;

import com.etu.api.dtos.CreateTaskDto;
import com.etu.api.dtos.JwtResponse;
import com.etu.api.dtos.SolutionRequest;
import com.etu.api.dtos.TaskDto;
import com.etu.api.exceptions.ErrorDto;
import com.etu.api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "task-controller")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Get all tasks")
    @ApiResponse(responseCode = "200", content = @Content(
            array = @ArraySchema(schema = @Schema(implementation = TaskDto.class))))
    public ResponseEntity<?> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Get task by id", description = "Get task by id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = TaskDto.class)))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getTaskById(@PathVariable("id") int id){
        return taskService.loadTaskById(id);
    }

    @PostMapping()
    @Operation(summary = "Create task", description = "Create task")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = TaskDto.class)))
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto createTaskDto){
        return taskService.createTask(createTaskDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update task by id", description = "Update task by id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(implementation = TaskDto.class)))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> updateTaskById(@PathVariable("id") Integer id, @RequestBody CreateTaskDto createTaskDto){
        return taskService.updateTaskById(id, createTaskDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id", description = "Delete task by id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "Task deleted")))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> deleteTaskById(@PathVariable("id") Integer id){
        return taskService.deleteTaskById(id);
    }

    @PostMapping("/solution")
    @Operation(summary = "Solve the task by id", description = "Solve the task by id")
    @ApiResponse(responseCode = "200", content = @Content(
            schema = @Schema(example = "75")))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> solutionTask(@RequestBody SolutionRequest solutionRequest){
        return taskService.solutionTask(solutionRequest);
    }
}
