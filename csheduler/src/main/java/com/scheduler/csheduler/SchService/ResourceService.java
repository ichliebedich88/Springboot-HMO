package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.*;
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

    //jinming
    @Override
    public List<Resource> getResourcesInRoom(Integer id)
    {
        return resourceDao.getResourcesInRoom(id);
    }

    //jinming
    @Override
    public List<Resource> getResourceUsed()
    {
        return resourceDao.getResourceUsed();
    }

    //jinming
    @Override
    public int unboundRes(Integer id)
    {
        return resourceDao.unboundRes(id);
    }

    @Override
    public List<Resource> getResByRoomId(Integer roomId){
        return resourceDao.getResByRoomId(roomId);
    }

    @Override
    public int unbindResource(Integer id) {
        return resourceDao.unbindResource(id);
    }

    @Override
    public List<Resource> getResByType(String type) {
        return resourceDao.getResByType(type);
    }

    @Override
    public Task getTaskById(Integer id){
        return resourceDao.getTaskById(id);
    }

    @Override
    public int updateLog(Log log){
        return resourceDao.updateLog(log);
    }

    @Override
    public int bindResource(Integer id,Integer taskId,Integer dagId) {
        return resourceDao.bindResource(id,taskId,dagId);
    }
}
