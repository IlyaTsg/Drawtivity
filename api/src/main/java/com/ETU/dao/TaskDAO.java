package com.ETU.dao;

import com.ETU.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Service
@Transactional
public class TaskDAO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Task> getAllTasks(){
        return hibernateTemplate.loadAll(Task.class);
    }

    public Task getTaskById(int id){
        return hibernateTemplate.get(Task.class, id);
    }

    public int addTask(Task task){
        return (int) hibernateTemplate.save(task);
    }

    public void updateTask(Task task){
        hibernateTemplate.update(task);
    }

    public void deletebyTaskId(int id){
        Task task = hibernateTemplate.get(Task.class, id);
        if(task != null){
            hibernateTemplate.delete(task);
        }
    }
}
