package com.ETU.Drawtivity.API.repositories;

import com.ETU.Drawtivity.API.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
