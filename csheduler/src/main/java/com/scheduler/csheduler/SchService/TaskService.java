package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.Task;
import com.scheduler.csheduler.dao.TaskDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskDao {
    @Autowired
    TaskDao taskDao;

    @Override
    public List<Task> getTasksByDagId(Integer id) {
        return taskDao.getTasksByDagId(id);
    }

    @Override
    public int deleteTaskByDagId(Integer id) {
        return taskDao.deleteTaskByDagId(id);
    }

    @Override
    public int addTask(String name,Integer dag_id,String type,Float occupy_time){
        return taskDao.addTask(name,dag_id,type,occupy_time);
    }

    @Override
    public Task getNewTask(String name, Integer dagId, Float occupyTime, String type){
        return taskDao.getNewTask(name,dagId,occupyTime,type);
    }

}
