package com.etu.api.service;

import com.etu.api.dtos.CreateTaskDto;
import com.etu.api.dtos.SolutionRequest;
import com.etu.api.dtos.TaskDto;
import com.etu.api.entities.Point;
import com.etu.api.entities.Task;
import com.etu.api.exceptions.ErrorDto;
import com.etu.api.repositories.PointReposiroty;
import com.etu.api.repositories.TaskRepository;
import com.etu.api.utils.ImageUtils;
import com.etu.api.utils.TaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final PointReposiroty pointReposiroty;
    private final ImageUtils imageUtils;

    private final TaskUtils taskUtils;

    @Autowired
    public TaskService(TaskRepository taskRepository, PointReposiroty pointReposiroty, ImageUtils imageUtils, TaskUtils taskUtils) {
        this.taskRepository = taskRepository;
        this.pointReposiroty = pointReposiroty;
        this.imageUtils = imageUtils;
        this.taskUtils = taskUtils;
    }

    public ResponseEntity<?> loadTaskById(Integer task_id){
        Task task = taskRepository.findById(task_id).orElse(null);
        if(task != null){
            return ResponseEntity.ok(new TaskDto(task.getTask_id(), task.getOwner_id(), task.getTitle(), task.getDescription(), task.getCategory(), task.getType(), imageUtils.convertBlobToBase64(task.getImage()), task.getDeviation(), task.getPoints()));
        }
        else{
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getAllTasks(){
        List<TaskDto> tasks = taskRepository.findAll().stream().map(TaskDto::new).toList();
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<?> createTask(CreateTaskDto createTaskDto){
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
                null
        );
        for (Point point: createTaskDto.getPoints()) {
            point.setTask(task);
            points.add(point);
        }
        task.setPoints(points);
        taskRepository.save(task);
        pointReposiroty.saveAll(points);

        return ResponseEntity.ok(new TaskDto(task));
    }

    @Transactional
    public ResponseEntity<?> updateTaskById(Integer task_id, CreateTaskDto createTaskDto){
        Task task = taskRepository.findById(task_id).orElse(null);
        if(task == null){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
        task.setOwner_id(createTaskDto.getOwner_id());
        task.setTitle(createTaskDto.getTitle());
        task.setDescription(createTaskDto.getDescription());
        task.setCategory(createTaskDto.getCategory());
        task.setType(createTaskDto.getType());
        task.setImage(imageUtils.convertBase64ToBlob(createTaskDto.getImage()));
        task.setDeviation(createTaskDto.getDeviation());

        // Для обновления точек доастаем текущие точки задачи
        // Удаляем их
        // Сохраняем новые
        List<Point> points = new ArrayList<>();
        pointReposiroty.deleteAll(pointReposiroty.findByTaskId(task_id));
        for (Point point: createTaskDto.getPoints()) {
            point.setTask(task);
            points.add(point);
        }
        task.setPoints(points);
        taskRepository.save(task);
        pointReposiroty.saveAll(points);
        return ResponseEntity.ok(new TaskDto(task));
    }

    @Transactional
    public ResponseEntity<?> deleteTaskById(Integer task_id){
        Task task = taskRepository.findById(task_id).orElse(null);
        if(task == null){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }
        taskRepository.delete(task);
        return ResponseEntity.ok("Task deleted");
    }

    public ResponseEntity<?> solutionTask(SolutionRequest solutionRequest){
        Task task = taskRepository.findById(solutionRequest.getTask_id()).orElse(null);
        if(task == null){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), "Task not found"), HttpStatus.NOT_FOUND);
        }

        double result = 0D;
        if (Objects.equals(task.getType(), "Linear")){
            result = taskUtils.linearTaskSolution(task, solutionRequest.getPoints());
        } else if (Objects.equals(task.getType(), "Overlap")) {
            result = taskUtils.overlapTaskSolution(task, solutionRequest.getPoints());
        }

        return ResponseEntity.ok(result);
    }
}
