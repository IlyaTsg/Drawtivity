package com.ETU.DemoApi.service;

import com.ETU.DemoApi.dtos.CreateTaskDto;
import com.ETU.DemoApi.dtos.TaskDto;
import com.ETU.DemoApi.entities.Point;
import com.ETU.DemoApi.entities.Task;
import com.ETU.DemoApi.exceptions.ErrorDto;
import com.ETU.DemoApi.repositories.PointReposiroty;
import com.ETU.DemoApi.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final PointReposiroty pointReposiroty;

    @Autowired
    public TaskService(TaskRepository taskRepository, PointReposiroty pointReposiroty) {
        this.taskRepository = taskRepository;
        this.pointReposiroty = pointReposiroty;
    }

    public ResponseEntity<?> loadTaskById(Integer task_id){
        Task task = taskRepository.findById(task_id).orElse(null);
        if(task != null){
            return ResponseEntity.ok(new TaskDto(task.getTask_id(), task.getOwner_id(), task.getTitle(), task.getDescription(), task.getCategory(), task.getType(), task.getImg_url(), task.getDeviation(), task.getPoints()));
        }
        else{
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NO_CONTENT.value(), "Task not found"), HttpStatus.NO_CONTENT);
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
                createTaskDto.getImg_url(),
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
}
