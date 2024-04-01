package com.etu.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.sql.Blob;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "task_id")
    private Integer task_id;
    @Column(name = "owner_id")
    private Integer owner_id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "type")
    private String type;
    @Column(name = "image")
    @Lob
    private Blob image;
    @Column(name = "deviation")
    private Float deviation;
    @Column(name = "line_color")
    private String line_color;
    @Column(name = "fill_color")
    private String fill_color;
    @OneToMany(mappedBy = "task",cascade=CascadeType.ALL)
    private List<Point> points;

    public Task() {
    }

    public Task(Integer task_id, Integer owner_id, String title, String description, String category, String type, Blob image, Float deviation, List<Point> points, String line_color, String fill_color) {
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

    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
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
