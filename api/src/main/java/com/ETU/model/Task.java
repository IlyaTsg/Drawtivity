package com.ETU.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Task {
    private int task_id;
    private int owner_id;
    private String title;
    private String description;
    private String category;
    private String type;
    private String img_url;
    private float deviation;

    private ArrayList<Point> points;

    public Task(){}

    public Task(int task_id, int owner_id, String title, String description, String category, String type, String img_url, float deviation) {
        this.task_id = task_id;
        this.owner_id = owner_id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.img_url = img_url;
        this.deviation = deviation;
    }

    public Task(int task_id, int owner_id, String title, String description, String category, String type, String img_url, float deviation, ArrayList<Point> points) {
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

    public int getTask_id() {
        return task_id;
    }
    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(int owner_id) {
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

    public float getDeviation() {
        return deviation;
    }
    public void setDeviation(float deviation) {
        this.deviation = deviation;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
    public void setPointsSave(ArrayList<Point> points) {
        this.points = points;
    }
    public void setPointsShow(String JsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.points = objectMapper.readValue(JsonString, new TypeReference<ArrayList<Point>>(){});
    }
    public String ListTOJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this.points);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
