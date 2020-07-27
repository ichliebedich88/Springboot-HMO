package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.Res_Room_Task;
import com.scheduler.csheduler.dao.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService implements ResourceDao{
    @Autowired
    private ResourceDao resourceDao;
    @Override
    public List<Res_Room_Task> getResources() {
        List<Res_Room_Task> res = resourceDao.getResources();
        for(Res_Room_Task rrt:res){
            System.out.println(rrt.getOccupyTime());
            System.out.println(rrt.getRoomId());
        }
        return resourceDao.getResources();
    }
}
