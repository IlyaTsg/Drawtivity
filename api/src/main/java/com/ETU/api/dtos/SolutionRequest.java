package com.ETU.api.dtos;

import com.ETU.api.entities.Point;
import lombok.Data;

import java.util.List;

@Data
public class SolutionRequest {
    private Integer task_id;
    private List<Point> points;

    public SolutionRequest(Integer task_id, List<Point> points) {
        this.task_id = task_id;
        this.points = points;
    }

    public Integer getTask_id() {
        return task_id;
    }
    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public List<Point> getPoints() {
        return points;
    }
    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
