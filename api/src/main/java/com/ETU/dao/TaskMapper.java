package com.ETU.dao;

import com.ETU.model.Point;
import com.ETU.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task(rs.getInt("task_id"),
                rs.getInt("owner_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getString("type"),
                rs.getString("img_url"),
                rs.getFloat("deviation"));
        try {
            task.setPointsShow(rs.getString("points"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return task;
    }
}
