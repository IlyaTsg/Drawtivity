package com.ETU.api.entities;

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
}
