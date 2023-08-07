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
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
    private UserDAO userDAO;
    private TaskDAO taskDAO;

    @Autowired
    public UserController(UserDAO userDAO, TaskDAO taskDAO) {
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
    }

    @GetMapping(produces = "application/json")
    public List<User> index() throws IOException {
        return userDAO.getAllUsers();
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public User show(@PathVariable("id") int id){
        return userDAO.getUserById(id);
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public List<Task> getTasksByUserId(@PathVariable("id") int id){
        return taskDAO.getTasksByOwnerId(id);
    }


    @PostMapping(consumes = "application/json")
    public int addUser(@RequestBody User user){
        return userDAO.addUser(user);
    }

    @PatchMapping("/{id}")
    public int updateUser(@PathVariable("id") int id, @RequestBody User user){
        user.setUser_id(id);
        userDAO.updateUser(user);
        return 200;
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable("id") int id){
        userDAO.deleteUserById(id);
        return  204;
    }

    @PostMapping(value = "/auth",consumes = "application/json")
    public int Auth(@RequestBody User user){
        return userDAO.auth(user);
    }
}
