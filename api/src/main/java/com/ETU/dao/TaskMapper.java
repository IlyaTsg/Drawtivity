package com.ETU.dao;

import com.ETU.model.Task;
import com.ETU.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                rs.getInt("task_id"),
                rs.getInt("owner_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("category"),
                rs.getString("type"),
                rs.getString("img_url"),
                rs.getFloat("deviation")
        );
    }
}
