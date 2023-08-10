package com.ETU.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @JsonIgnore
    private int point_id;

    private int x,y;

    public Point() {}

    public Point(int point_id, int x, int y) {
        this.point_id = point_id;
        this.x = x;
        this.y = y;
    }

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
