package com.etu.api.dtos;

import com.etu.api.entities.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Create task request")
public class CreateTaskDto {
    @Schema(type = "int", example = "4")
    private Integer owner_id;
    @Schema(type = "string", example = "testTitle")
    private String title;
    @Schema(type = "string", example = "testDescription")
    private String description;
    @Schema(type = "string", example = "testCategory")
    private String category;
    @Schema(type = "string", example = "testType")
    private String type;
    @Schema(type = "string", example = "iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAALUlEQVR4nGJ5dk6eARV8ZD")
    private String image;
    @Schema(type = "float", example = "12.4")
    private Float deviation;
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

    public CreateTaskDto(Integer owner_id, String title, String description, String category, String type, String image, Float deviation, List<Point> points) {
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.image = image;
        this.deviation = deviation;
        this.points = points;
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

    public List<Point> getPoints() {
        return points;
    }
    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
