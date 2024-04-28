package com.etu.api.service;

import com.etu.api.dtos.user.UserDto;
import com.etu.api.dtos.user.UserRegResp;
import com.etu.api.dtos.user.UserRegDto;
import com.etu.api.dtos.user.UserRoleUpdReq;
import com.etu.api.entities.Role;
import com.etu.api.entities.User;
import com.etu.api.exceptions.ErrorDto;
import com.etu.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /** Получение данных пользователя по email*/
    public User getUser(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> getAllUsers(){
        List<UserDto> users = userRepository.findAll().stream()
                .map(UserDto::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    /** Обновление ролей пользователя */
    @Transactional
    public ResponseEntity<?> updateRoleByUserId(Integer user_id, UserRoleUpdReq userRoleUpdReq) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null) {
            // Находим роли по именам из запроса
            List<Role> roles = userRoleUpdReq.getRoles().stream()
                    .map(roleService::findByName)
                    .filter(Optional::isPresent)
                    .map(Optional::get).toList();
            if (!roles.isEmpty()) {
                user.setRoles(new ArrayList<>(roles));
                userRepository.save(user);
            }
            return ResponseEntity.ok("User roles updated successfully.");
        } else {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public User addUser(UserRegDto userRegDto){
        // Добавить exception если user с таким email уже есть
        User user = new User(
                null,
                userRegDto.getFirstname(),
                userRegDto.getLastname(),
                userRegDto.getEmail(),
                passwordEncoder.encode(userRegDto.getPassword()),
                List.of(roleService.findByName("ROLE_USER").get())
        );
        return userRepository.save(user);
    }
}
