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
    @Schema(type = "string", example = "Linear", description = "Может быть тип Linear или Overlap")
    private String type;
    @Schema(type = "string", example = "iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAIAAADZF8uwAAAALUlEQVR4nGJ5dk6eARV8ZD")
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

    public CreateTaskDto(Integer owner_id, String title, String description, String category, String type, String image, Float deviation, List<Point> points, String line_color, String fill_color) {
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
}
