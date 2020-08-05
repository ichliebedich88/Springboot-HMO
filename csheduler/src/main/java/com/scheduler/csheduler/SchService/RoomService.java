package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.Dag;
import com.scheduler.csheduler.bean.Room;
import com.scheduler.csheduler.dao.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements  RoomDao {
    @Autowired
    public RoomDao roomDao;

    @Override
    public List<Room> getRoomsFree(){
        return roomDao.getRoomsFree();
    }

    @Override
    public int bindRoom(Integer id)
    {
        return roomDao.bindRoom(id);
    }

    @Override
    public List<Room> getRoomsBound(){
        return roomDao.getRoomsBound();
    }

    @Override
    public int unboundRoom(Integer id)
    {
        return roomDao.unboundRoom(id);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomDao.getAllRoom();
    }

    @Override
    public Dag getDagByRoomId(Integer roomId) {
        return roomDao.getDagByRoomId(roomId);
    }

}
