package com.ETU.controller;

import com.ETU.dao.TaskDAO;
import com.ETU.model.Point;
import com.ETU.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class TaskController {
    private TaskDAO taskDAO;

    @Autowired
    public TaskController(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }

    @GetMapping(produces = "application/json")
    public List<Task> index(){
        return taskDAO.getAllTasks();
    }
    @GetMapping(value="/{id}", produces = "application/json")
    public Task show(@PathVariable("id") int id){
        return taskDAO.getTaskById(id);
    }

    @PostMapping(consumes = "application/json")
    public int addTask(@RequestBody Task task){
        return taskDAO.addTask(task);
    }

    @PatchMapping("/{id}")
    public int updateTask(@PathVariable("id") int id, @RequestBody Task task){
        task.setTask_id(id);
        taskDAO.updateTask(task);
        return 200;
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable("id") int id){
        taskDAO.deletebyTaskId(id);
        return 204;
    }

    @GetMapping(value = "/solution", consumes = "application/json", produces = "application/json")
    public float solution(@RequestBody JsonNode req_json) throws IOException {
        int task_id = req_json.get("task_id").intValue();
        Task task = taskDAO.getTaskById(task_id);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<Point>>() {});
        List<Point> solution_points = reader.readValue(req_json.get("points"));

        int count_right = 0;
        for(int j=0; j<task.getPoints().size();j++){
            for (Point solutionPoint : solution_points) {
                double x_d = (solutionPoint.getX() - task.getPoints().get(j).getX()) * (solutionPoint.getX() - task.getPoints().get(j).getX());
                double y_d = (solutionPoint.getY() - task.getPoints().get(j).getY()) * (solutionPoint.getY() - task.getPoints().get(j).getY());
                double r_d = task.getDeviation() * task.getDeviation();
                if (x_d + y_d <= r_d) {
                    count_right += 1;
                    break;
                }
            }
        }
        return ((float)count_right/(float)(task.getPoints().size()))*100;
    }
}
