package com.ETU.DemoApi.repositories;

import com.ETU.DemoApi.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointReposiroty extends JpaRepository<Point, Integer> {
}
