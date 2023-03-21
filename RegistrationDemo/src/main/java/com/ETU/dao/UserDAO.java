package com.ETU.dao;

import com.ETU.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private final List<User> users;
    private int cars_count;

    public UserDAO() {
        users = new ArrayList<>();
        users.add(new User(cars_count++, "Michael", "Canada"));
        users.add(new User(cars_count++, "Tom", "USA"));
        users.add(new User(cars_count++, "Jim", "UK"));
        users.add(new User(cars_count++, "Steve", "France"));
    }

    // Возвращаем весь список
    public List<User> index(){
        return users;
    }

    // Возвращаем одну по id
    public User show(int id){
        User toReturn = new User();
        for(User user: users){
            if (user.getUserNo() == id) {
                toReturn = user;
                break;
            }
        }
        return toReturn;
    }

    // Сохранение одной в БД
    public void save(User user){
        user.setUserNo(cars_count++);
        users.add(user);
    }

    // Изменение информации
    public void edit(User user){
        User userToUpdate = show(user.getUserNo());
        userToUpdate.setName(user.getName());
        userToUpdate.setCountry(user.getCountry());
    }

    // Удаление элемента из БД
    public void delete(int id){
        User toRemove = new User();
        for(User user: users){
            if (user.getUserNo() == id) {
                toRemove = user;
                break;
            }
        }
        users.remove(toRemove);
    }
}
