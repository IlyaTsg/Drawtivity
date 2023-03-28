package com.ETU.dao;

import com.ETU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index(){
        return jdbcTemplate.query("select * from users", new UserMapper());
    }

    public User show(int id){
        List<User> response = jdbcTemplate.query("select user_id, firstname, lastname, email, password " +
                                      "from users " +
                                      "where user_id=?", new UserMapper(), id);
        return response.get(0);
    }

    public void save(User user){
        jdbcTemplate.update("insert into users (firstname, lastname, email, password) values(?,?,?,?)",
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
    }

    public void edit(User user){
        jdbcTemplate.update("update users set firstname=?, lastname=?, email=?, password=? where user_id=?",
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getUser_id());
    }

    public void delete(int id){
        jdbcTemplate.update("delete from users where user_id=?", id);
    }
}
