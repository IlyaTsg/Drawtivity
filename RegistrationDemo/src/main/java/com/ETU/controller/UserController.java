package com.ETU.controller;

import com.ETU.dao.UserDAO;
import com.ETU.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO;

    ObjectMapper objectMapper = new ObjectMapper();

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
    public String addUser(@RequestBody User user){ // Берем уже заполненную car из Model
        userDAO.save(user);
        return "OK";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable("id") int id, @RequestBody User user){
        userDAO.edit(user);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userDAO.delete(id); // Уадляем из БД
        return  "OK";
    }
}
