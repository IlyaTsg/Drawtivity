package com.ETU.Drawtivity.API.repositories;

import com.ETU.Drawtivity.API.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointReposiroty extends JpaRepository<Point, Integer> {
}
