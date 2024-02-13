package com.etu.api.service;

import com.etu.api.dtos.JwtRequest;
import com.etu.api.dtos.RegistrationUserDto;
import com.etu.api.entities.User;
import com.etu.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    /**
     * Получение данных пользователя
     * @param email
     * @return User
     */
    public User getUser(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    @Transactional
    public User addUser(RegistrationUserDto registrationUserDto){
        // Добавить exception если user с таким email уже есть
        User user = new User(
                null,
                registrationUserDto.getFirstname(),
                registrationUserDto.getLastname(),
                registrationUserDto.getEmail(),
                passwordEncoder.encode(registrationUserDto.getPassword()),
                List.of(roleService.findByName("ROLE_USER").get())
        );
        return userRepository.save(user);
    }
}
