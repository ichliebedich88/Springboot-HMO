package com.scheduler.csheduler.controller;

import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.SchService.RoomService;
import com.scheduler.csheduler.bean.Dag;
import com.scheduler.csheduler.bean.Room;
import com.scheduler.csheduler.bean.RoomINS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/room/list")
    public Result getRoomList(){
        List<Room> rooms = roomService.getAllRoom();
        List<RoomINS> roomList = new ArrayList<>();
        for(Room tmp:rooms){
            RoomINS roomINS = new RoomINS();
            roomINS.setId(tmp.getId());
            if(tmp.getName()!=null){
                roomINS.setName(tmp.getName());
            }
            if(tmp.getStatus()!=null){
                roomINS.setStatus(tmp.getStatus());
            }
            Dag dag = roomService.getDagByRoomId(tmp.getId());
            if(roomINS.getStatus()!=0){
                if(dag!=null){
                    roomINS.setDag("流程"+dag.getId());
                }else roomINS.setDag("");
            }else roomINS.setDag("");

            roomList.add(roomINS);
        }
        return Result.success().add("room list",roomList);
    }

}
