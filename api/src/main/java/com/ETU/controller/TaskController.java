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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskDAO taskDAO;

    @Autowired
    public TaskController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public Task show(@PathVariable("id") int id){
        return taskDAO.show(id);
    }

    @GetMapping(produces = "application/json")
    public List<Task> index() throws IOException {
        return taskDAO.index();
    }

    @PostMapping(consumes = "application/json")
    public int addTask(@RequestBody Task task) {
        return taskDAO.save(task);
    }

    @DeleteMapping("/{id}")
    public int deleteTask(@PathVariable("id") int id){
        taskDAO.delete(id);
        return  204;
    }

    @GetMapping(value = "solution", consumes = "application/json", produces = "application/json")
    public float solution(@RequestBody JsonNode req_json) throws IOException {
        int task_id = req_json.get("task_id").intValue();
        Task task = taskDAO.show(task_id);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<ArrayList<Point>>() {});
        ArrayList<Point> solution_points = reader.readValue(req_json.get("points"));

        int count_right = 0;
        for(int j=0; j<task.getPoints().size();j++){
            for(int i=0; i<solution_points.size();i++){
                double x_d = (solution_points.get(i).getX()-task.getPoints().get(j).getX())*(solution_points.get(i).getX()-task.getPoints().get(j).getX());
                double y_d = (solution_points.get(i).getY()-task.getPoints().get(j).getY())*(solution_points.get(i).getY()-task.getPoints().get(j).getY());
                double r_d = task.getDeviation()*task.getDeviation();
                if(x_d+y_d<=r_d){
                    count_right += 1;
                    break;
                }
            }
        }
        return ((float)count_right/(float)(task.getPoints().size()))*100;
    }
}
