package com.ETU.api.service;

import com.ETU.api.dtos.CreateTaskDto;
import com.ETU.api.dtos.SolutionRequest;
import com.ETU.api.dtos.TaskDto;
import com.ETU.api.entities.Grade;
import com.ETU.api.entities.Point;
import com.ETU.api.entities.Task;
import com.ETU.api.entities.User;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.repositories.PointReposiroty;
import com.ETU.api.repositories.TaskRepository;
import com.ETU.api.utils.ImageUtils;
import com.ETU.api.utils.TaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ilya Tsygankov
 * @created 19.08.2023
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final PointReposiroty pointReposiroty;
    private final GradeService gradeService;
    private final UserService userService;
    private final ImageUtils imageUtils;
    private final TaskUtils taskUtils;

    @Autowired
    public TaskService(TaskRepository taskRepository, PointReposiroty pointReposiroty, GradeService gradeService, UserService userService, ImageUtils imageUtils, TaskUtils taskUtils) {
        this.taskRepository = taskRepository;
        this.pointReposiroty = pointReposiroty;
        this.gradeService = gradeService;
        this.userService = userService;
        this.imageUtils = imageUtils;
        this.taskUtils = taskUtils;
    }

    public ResponseEntity<?> loadTaskById(Integer task_id) {
        Task task = taskRepository.findById(task_id).orElse(null);
        if (task != null) {
            return ResponseEntity.ok(new TaskDto(task.getTask_id(),
                    task.getOwner_id(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getCategory(),
                    task.getType(),
                    imageUtils.convertBlobToBase64(task.getImage()),
                    task.getDeviation(),
                    task.getPoints(),
                    task.getLine_color(),
                    task.getFill_color()));
        } else {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getAllTasks() {
        List<TaskDto> tasks = taskRepository.findAll().stream().map(TaskDto::new).toList();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<?> createTask(CreateTaskDto createTaskDto) {
        List<Point> points = new ArrayList<>();
        Task task = new Task(
                null,
                createTaskDto.getOwner_id(),
                createTaskDto.getTitle(),
                createTaskDto.getDescription(),
                createTaskDto.getCategory(),
                createTaskDto.getType(),
                imageUtils.convertBase64ToBlob(createTaskDto.getImage()),
                createTaskDto.getDeviation(),
                null,
                createTaskDto.getLine_color(),
                createTaskDto.getFill_color()
        );
        for (Point point : createTaskDto.getPoints()) {
            point.setTask(task);
            points.add(point);
        }
        task.setPoints(points);
        taskRepository.save(task);
        pointReposiroty.saveAll(points);

        return ResponseEntity.ok(new TaskDto(task));
    }

    @Transactional
    public ResponseEntity<?> updateTaskById(Integer task_id, CreateTaskDto createTaskDto) {
        Task task = taskRepository.findById(task_id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
        task.setOwner_id(createTaskDto.getOwner_id());
        task.setTitle(createTaskDto.getTitle());
        task.setDescription(createTaskDto.getDescription());
        task.setCategory(createTaskDto.getCategory());
        task.setType(createTaskDto.getType());
        task.setImage(imageUtils.convertBase64ToBlob(createTaskDto.getImage()));
        task.setDeviation(createTaskDto.getDeviation());
        task.setLine_color(createTaskDto.getLine_color());
        task.setFill_color(createTaskDto.getFill_color());

        // Для обновления точек доастаем текущие точки задачи
        // Удаляем их
        // Сохраняем новые
        List<Point> points = new ArrayList<>();
        pointReposiroty.deleteAll(pointReposiroty.findByTaskId(task_id));
        for (Point point : createTaskDto.getPoints()) {
            point.setTask(task);
            points.add(point);
        }
        task.setPoints(points);
        taskRepository.save(task);
        pointReposiroty.saveAll(points);
        return ResponseEntity.ok(new TaskDto(task));
    }

    @Transactional
    public ResponseEntity<?> deleteTaskById(Integer task_id) {
        Task task = taskRepository.findById(task_id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
        taskRepository.delete(task);
        return ResponseEntity.ok("Task deleted");
    }

    public ResponseEntity<?> solutionTask(SolutionRequest solutionRequest) {
        Task task = taskRepository.findById(solutionRequest.getTask_id()).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }

        double result = 0D;
        if (Objects.equals(task.getType(), "Linear")) {
            result = taskUtils.linearTaskSolution(task, solutionRequest.getPoints());
        } else if (Objects.equals(task.getType(), "Overlap")) {
            result = taskUtils.overlapTaskSolution(task, solutionRequest.getPoints());
        }

        // Достаем пользователя текущей сессии
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(email);

        // Сохраняем оценку пользователя
        // TODO Исправить функции решения задач, чтобы возвращали Float
        try {
            /** TODO
             * Пока что удаляем текущую оценку и добавялем новую
             * Нужно сделать количество попыток для задачи и пользователя
             * Скорее всего хранить их также в Grades
             */
            Grade grade = gradeService.getGrade(user, task);
            if (grade != null) {
                gradeService.deleteGrade(grade);
            }

            gradeService.addGrade(new Grade(user, task, (float) result));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDto(HttpStatus.FORBIDDEN.value(), "Task already solved"), HttpStatus.FORBIDDEN);
        }

        // TODO Тут стоит возвращать Grade
        return ResponseEntity.ok(result);
    }
}
