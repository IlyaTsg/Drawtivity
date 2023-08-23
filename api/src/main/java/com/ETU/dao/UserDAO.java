package com.ETU.dao;

import com.ETU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@Component
public class UserDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public User getUserById(int uid){
        return hibernateTemplate.get(User.class, uid);
    }

    public List<User> getAllUsers(){
        return hibernateTemplate.loadAll(User.class);
    }

    public int addUser(User user){
        return (int) hibernateTemplate.save(user);
    }

    public void updateUser(User user){
        hibernateTemplate.update(user);
    }

    public void deleteUserById(int id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user != null) {
            hibernateTemplate.delete(user);
        }
    }

    public int auth(User user){
        String query = "FROM User WHERE email = ?0 AND password = ?1";
        List<User> users = (List<User>) hibernateTemplate.find(query, user.getEmail(), user.getPassword());

        if (!users.isEmpty()) {
            return users.get(0).getUser_id(); // Возвращаем id найденного пользоваля
        }

        return -1; // Если пользователь не найден
    }
}
