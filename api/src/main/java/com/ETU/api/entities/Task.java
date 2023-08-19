package com.ETU.api.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "img_url")
    private String img_url;
    @Column(name = "deviation")
    private Float deviation;

    @OneToMany(mappedBy = "task",cascade=CascadeType.ALL)
    private List<Point> points;

    public Task() {
    }

    public Task(Integer task_id, Integer owner_id, String title, String description, String category, String type, String img_url, Float deviation, List<Point> points) {
        this.task_id = task_id;
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.img_url = img_url;
        this.deviation = deviation;
        this.points = points;
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

    public String getImg_url() {
        return img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
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
