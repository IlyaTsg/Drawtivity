package com.etu.api;

import com.etu.api.dtos.user.UserRegDto;
import com.etu.api.entities.Grade;
import com.etu.api.entities.Task;
import com.etu.api.entities.User;
import com.etu.api.repositories.GradeRepository;
import com.etu.api.repositories.TaskRepository;
import com.etu.api.repositories.UserRepository;
import com.etu.api.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCors() throws Exception{
        UserRegDto user = new UserRegDto("John", "Doe", "john@gmail.com", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user);

        this.mockMvc
                .perform(options("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Access-Control-Request-Method", "POST")
                        .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testSaveAndFindByUserAndTask() {
        User user = userRepository.findById(8).orElse(null);

        Task task = taskRepository.findById(8).orElse(null);

        Float gradeValue = 4.5f;

        Grade foundGrade = gradeRepository.findByUserAndTask(user, task);

        assertEquals(user, foundGrade.getUser());
        assertEquals(task, foundGrade.getTask());
        assertEquals(gradeValue, foundGrade.getGrade());
    }

}
