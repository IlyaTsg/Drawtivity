package com.ETU.api.service;

import com.ETU.api.dtos.CreateTaskDto;
import com.ETU.api.dtos.SolutionRequest;
import com.ETU.api.dtos.TaskDto;
import com.ETU.api.entities.Point;
import com.ETU.api.entities.Task;
import com.ETU.api.exceptions.ErrorDto;
import com.ETU.api.repositories.PointReposiroty;
import com.ETU.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public ResponseEntity<?> updateTaskById(Integer task_id, CreateTaskDto createTaskDto){
        Task task = taskRepository.findById(task_id).orElse(null);
        if(task == null){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NO_CONTENT.value(), "Task not found"), HttpStatus.NO_CONTENT);
        }
        task.setOwner_id(createTaskDto.getOwner_id());
        task.setTitle(createTaskDto.getTitle());
        task.setDescription(createTaskDto.getDescription());
        task.setCategory(createTaskDto.getCategory());
        task.setType(createTaskDto.getType());
        task.setImg_url(createTaskDto.getImg_url());
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
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NO_CONTENT.value(), "Task not found"), HttpStatus.NO_CONTENT);
        }
        taskRepository.delete(task);
        return ResponseEntity.ok("Task deleted");
    }

    public ResponseEntity<?> solutionTask(SolutionRequest solutionRequest){
        Task task = taskRepository.findById(solutionRequest.getTask_id()).orElse(null);
        if(task == null){
            return new ResponseEntity<>(new ErrorDto(HttpStatus.NO_CONTENT.value(), "Task not found"), HttpStatus.NO_CONTENT);
        }

        int count_right = 0;
        for(int j=0; j<task.getPoints().size();j++){
            for (Point solutionPoint : solutionRequest.getPoints()) {
                double x_d = (solutionPoint.getX() - task.getPoints().get(j).getX()) * (solutionPoint.getX() - task.getPoints().get(j).getX());
                double y_d = (solutionPoint.getY() - task.getPoints().get(j).getY()) * (solutionPoint.getY() - task.getPoints().get(j).getY());
                double r_d = task.getDeviation() * task.getDeviation();
                if (x_d + y_d <= r_d) {
                    count_right += 1;
                    break;
                }
            }
        }
        return ResponseEntity.ok((float)count_right/(float)(task.getPoints().size())*100);
    }
}
