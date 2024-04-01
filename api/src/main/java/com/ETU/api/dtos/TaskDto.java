package com.etu.api.dtos;

import com.etu.api.entities.Point;
import com.etu.api.entities.Task;
import com.etu.api.utils.ImageUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Task response")
public class TaskDto {
    @Schema(type = "int", example = "2")
    private Integer task_id;
    @Schema(type = "int", example = "4")
    private Integer owner_id;

    @Schema(type = "string", example = "testTitle")
    private String title;
    @Schema(type = "string", example = "testDescription")
    private String description;
    @Schema(type = "string", example = "testCategory")
    private String category;
    @Schema(type = "string", example = "Linear", description = "Может быть тип Linear или Overlap")
    private String type;
    @Schema(type = "string", example = "iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAALUlEQVR4nGJ5dk6eARV8ZD+EJsLEQAQYjIpY/vAfQxNqMHegmXV0VgQIAAD//3RABnK8byShAAAAAElFTkSuQmCC")
    private String image;
    @Schema(type = "float", example = "12.4")
    private Float deviation;

    @Schema(type = "string", example = "#FF0000")
    private String line_color;
    @Schema(type = "string", example = "#FF0000")
    private String fill_color;
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

    public TaskDto(Integer task_id, Integer owner_id, String title, String description, String category, String type, String image, Float deviation, List<Point> points, String line_color, String fill_color) {
        this.task_id = task_id;
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.image = image;
        this.deviation = deviation;
        this.points = points;
        this.line_color = line_color;
        this.fill_color = fill_color;
    }

    public TaskDto(Task task){
        this.task_id = task.getTask_id();
        this.owner_id = task.getOwner_id();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.category = task.getCategory();
        this.type = task.getType();
        this.image = new ImageUtils().convertBlobToBase64(task.getImage());
        this.deviation = task.getDeviation();
        this.points = task.getPoints();
        this.line_color = task.getLine_color();
        this.fill_color = task.getFill_color();
    }

    public Integer getTask_id() {
        return task_id;
    }
    public void setTask_id(Integer task_id) {
        this.task_id = task_id;
    }

    public Integer getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Float getDeviation() {
        return deviation;
    }
    public void setDeviation(Float deviation) {
        this.deviation = deviation;
    }

    public String getLine_color() {
        return line_color;
    }
    public void setLine_color(String line_color) {
        this.line_color = line_color;
    }

    public String getFill_color() {
        return fill_color;
    }
    public void setFill_color(String fill_color) {
        this.fill_color = fill_color;
    }

    public List<Point> getPoints() {
        return points;
    }
    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
