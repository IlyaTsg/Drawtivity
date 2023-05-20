package com.ETU.dao;

import com.ETU.model.Task;
import com.ETU.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> index(){
        return jdbcTemplate.query("select * from tasks", new TaskMapper());
    }

    public int save(Task task){
        jdbcTemplate.update("insert into tasks (owner_id, title, description, category, type, img_url, deviation) values(?,?,?,?, ?,?,?)",
                task.getOwner_id(), task.getTitle(), task.getDescription(), task.getCategory(), task.getType(), task.getImg_url(), task.getDeviation());

        List<Task> response = jdbcTemplate.query("select * from tasks", new TaskMapper());
        return response.get(response.size()-1).getTask_id(); // Возвращаем id последнего в списке
    }

    public void delete(int id){
        jdbcTemplate.update("delete from tasks where task_id=?", id);
    }
}
