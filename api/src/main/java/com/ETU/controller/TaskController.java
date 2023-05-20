package com.ETU.controller;

import com.ETU.dao.TaskDAO;
import com.ETU.dao.UserDAO;
import com.ETU.model.Task;
import com.ETU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/tasks")
public class TaskController {
    private TaskDAO taskDAO;

    @Autowired
    public TaskController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping(produces = "application/json")
    public List<Task> index() throws IOException {
        return taskDAO.index();
    }

    @PostMapping(consumes = "application/json")
    public int addTask(@RequestBody Task task){
        return taskDAO.save(task);
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable("id") int id){
        taskDAO.delete(id);
        return  204;
    }
}
