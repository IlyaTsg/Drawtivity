package com.ETU.controller;

import com.ETU.dao.UserDAO;
import com.ETU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(produces = "application/json")
    public List<User> index() throws IOException {
        return userDAO.index();
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public User show(@PathVariable("id") int id){
        return userDAO.show(id);
    }

    @PostMapping(value = "/CreateUser", consumes = "application/json")
    public int addUser(@RequestBody User user){
        userDAO.save(user);
        return 200;
    }

    @PatchMapping("/{id}")
    public int updateUser(@PathVariable("id") int id, @RequestBody User user){
        userDAO.edit(user);
        return 200;
    }

    @DeleteMapping("/{id}")
    public int deleteUser(@PathVariable("id") int id){
        userDAO.delete(id);
        return  200;
    }
}
