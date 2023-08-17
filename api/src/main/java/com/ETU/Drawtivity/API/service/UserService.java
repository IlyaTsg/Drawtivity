package com.ETU.Drawtivity.API.service;

import com.ETU.Drawtivity.API.dtos.RegistrationUserDto;
import com.ETU.Drawtivity.API.entities.User;
import com.ETU.Drawtivity.API.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
