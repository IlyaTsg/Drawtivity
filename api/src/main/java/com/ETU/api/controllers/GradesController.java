package com.ETU.api.controllers;

import com.ETU.api.dtos.grade.GradeDto;
import com.ETU.api.entities.Grade;
import com.ETU.api.entities.User;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.service.GradeService;
import com.ETU.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
@Tag(name = "grades-controller")
public class GradesController {
    private final GradeService gradeService;
    private final UserService userService;

    @Autowired
    public GradesController(GradeService gradeService, UserService userService) {
        this.gradeService = gradeService;
        this.userService = userService;
    }

    @GetMapping("/user/{user_id}")
    @Operation(summary = "Все оценки по пользователю", description = "Все оценки по пользователю")
    @ApiResponse(responseCode = "200", content = @Content(
            array = @ArraySchema(schema = @Schema(implementation = GradeDto.class))))
    @ApiResponse(responseCode = "404", content = @Content(
            schema = @Schema(implementation = ErrorDto.class)))
    public ResponseEntity<?> getGradesByUser(@PathVariable("user_id") Integer user_id) {
        User user = userService.getUserById(user_id);
        if (user == null)
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "User not found"), HttpStatus.NOT_FOUND);
        else {
            List<Grade> grades = gradeService.getGradesByUser(user);
            if (grades.isEmpty())
                return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Grades not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(grades.stream().map(GradeDto::new), HttpStatus.OK);
        }
    }
}
