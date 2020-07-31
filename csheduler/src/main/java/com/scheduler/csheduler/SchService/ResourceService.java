package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.EditResource;
import com.scheduler.csheduler.bean.Res_Room;
import com.scheduler.csheduler.bean.Resource;
import com.scheduler.csheduler.bean.Room;
import com.scheduler.csheduler.dao.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService implements ResourceDao{
    @Autowired
    private ResourceDao resourceDao;
    @Override
    public List<Resource> getAllResource() {
        return resourceDao.getAllResource();
    }

    @Override
    public Room getRoomById(Integer id){
        return resourceDao.getRoomById(id);
    }

    @Override
    public Resource getResourceById(Integer id){
        return resourceDao.getResourceById(id);
    }

    @Override
    public Room getRoomByName(String name){
        return resourceDao.getRoomByName(name);
    }

    @Override
    public int edit(Resource resource){
        return resourceDao.edit(resource);
    }

    @Override
    public int delete(Integer id){
        return resourceDao.delete(id);
    }

    @Override
    public int add_res(Resource resource) {
        return resourceDao.add_res(resource);
    }

}
