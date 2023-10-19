package com.etu.api.dtos;

import com.etu.api.entities.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Task solution request")
public class SolutionRequest {
    @Schema(type = "int", example = "2")
    private Integer task_id;
    @Schema(example = "[\n" +
            "            {\n" +
            "                \"x\": 10.0,\n" +
            "                \"y\": 10.0\n" +
            "            },\n" +
            "            {\n" +
            "                \"x\": 15.0,\n" +
            "                \"y\": 15.0\n" +
            "            }]")
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
