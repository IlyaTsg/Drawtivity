package com.ETU.controller;

import com.ETU.dao.TaskDAO;
import com.ETU.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TaskController {
    private TaskDAO taskDAO;

    @Autowired
    public TaskController(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    @GetMapping(produces = "application/json")
    public List<Task> index(){
        return taskDAO.getAllTasks();
    }
    @GetMapping(value="/{id}", produces = "application/json")
    public Task show(@PathVariable("id") int id){
        return taskDAO.getTaskById(id);
    }

    @PostMapping(consumes = "application/json")
    public int addTask(@RequestBody Task task){
        return taskDAO.addTask(task);
    }

    @PatchMapping("/{id}")
    public int updateTask(@PathVariable("id") int id, @RequestBody Task task){
        task.setTask_id(id);
        taskDAO.updateTask(task);
        return 200;
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable("id") int id){
        taskDAO.deletebyTaskId(id);
        return 204;
    }
}
