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

    public int save(User user){
        jdbcTemplate.update("insert into users (firstname, lastname, email, password) values(?,?,?,?)",
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
        // ДОДЕЛАТЬ
        // Необходимо возвращать id нового пользователя
        // Т.к. последний пользователь может поменяться, то (надо использовать транзакции?)

        List<User> response = jdbcTemplate.query("select * from users", new UserMapper());
        return response.get(response.size()-1).getUser_id(); // Возвращаем id последнего в списке
    }

    public void edit(User user){
        jdbcTemplate.update("update users set firstname=?, lastname=?, email=?, password=? where user_id=?",
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getUser_id());
    }

    public void delete(int id){
        jdbcTemplate.update("delete from users where user_id=?", id);
    }
}
