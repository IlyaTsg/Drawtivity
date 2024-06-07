package com.ETU.api.dtos.grade;

import com.ETU.api.entities.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Ilya Tsygankov
 * @created 29.04.2024
 */
@Data
@Schema(description = "Оценка")
public class GradeDto {
    @Schema(type = "float", example = "35")
    private Float grade;
    @Schema(type = "int", example = "8")
    private Integer task_id;

    public GradeDto(Grade grade) {
        this.grade = grade.getGrade();
        this.task_id = grade.getTask().getTask_id();
    }
}