package com.ETU.api.dtos;

import com.ETU.api.entities.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Ilya Tsygankov
 * @created 19.08.2023
 */
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
}
