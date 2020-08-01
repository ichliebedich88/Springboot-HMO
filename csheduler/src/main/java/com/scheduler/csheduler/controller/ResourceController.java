package com.scheduler.csheduler.controller;

import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.SchService.ResourceService;
import com.scheduler.csheduler.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    /**
     * 查询所有资源
     * @return
     */
    @GetMapping("/resource/list")
    public Result getAllResource(){
        List<Res_Room> rrList = new ArrayList<>();
        List<Resource> resources = resourceService.getAllResource();
        for(Resource tmp:resources){
            Res_Room rrt = new Res_Room();
            if(tmp.getRoomId() != null){
                int roomId = tmp.getRoomId();
                Room room = resourceService.getRoomById(roomId);
                if(room!=null&&room.getName()!=null)
                    rrt.setRoom(room.getName());
            }
            rrt.setId(tmp.getId());
            if(tmp.getName()!=null)
                rrt.setName(tmp.getName());
            if(tmp.getType()!=null)
                rrt.setType(tmp.getType());
            if(tmp.getBrand()!=null)
                rrt.setBrand(tmp.getBrand());
            if(tmp.getStatus()!=null)
                rrt.setStatus(tmp.getStatus());
            if(tmp.getTaskId()!=null)
                rrt.setTask(tmp.getTaskId());
            rrList.add(rrt);
        }
        return Result.success().add("resource",rrList);
    }

    @PostMapping("/resource/edit")
    //更新资源页面
    public Result edit(@RequestParam("type") String type,
                       @RequestParam("heterogeneous") String brand,
                       @RequestParam("room_id") Integer roomId,
                       @RequestParam("name") String name,
                       @RequestParam("id") Integer id){
        Res_Room resRoom = new Res_Room();
        Resource resource = new Resource();
        resource.setBrand(brand);
        resource.setId(id);
        resource.setType(type);
        resource.setName(name);
        if(resourceService.getRoomById(roomId)!=null){//根据房间id可以找到房间的话
            if(resourceService.getRoomById(roomId).getName()!=null){//房间名不为空
                resource.setRoomId(roomId);//在资源记录中添加房间id
            }
        }
        resourceService.edit(resource);//更新新资源表
        resRoom.setBrand(brand);
        resRoom.setId(id);
        resRoom.setType(type);
        resRoom.setName(brand+type);
        if(resource.getRoomId()!=null){//更新网页
            resRoom.setRoom(resourceService.getRoomById(roomId).getName());
        }
        return Result.success();
    }

    @PostMapping("/resource/delete")
    public Result deleteResource(@RequestParam("id") Integer id){
        resourceService.delete(id);
        return Result.success();
    }

    @GetMapping("/resource/queryById")
    public Result ajax_edit_get(@RequestParam("id") Integer id){
        EditResource editResource = new EditResource();
        Resource resource = resourceService.getResourceById(id);
        editResource.setId(id);
        if(resource.getBrand()!=null&&resource.getType()!=null){
            editResource.setName(resource.getBrand()+resource.getType());
        }
        if(resource.getType()!=null){
            editResource.setType(resource.getType());
        }
        if(resource.getBrand()!=null){
            editResource.setBrand(resource.getBrand());
        }
        if(resource.getRoomId()!=null){
            editResource.setRoom(resource.getRoomId());
        }
        return Result.success().add("resource",editResource);
    }

    @PostMapping("/resource/add")
    public Result add_res(@RequestParam("type") String type,
                          @RequestParam("heterogeneous") String brand,
                          @RequestParam("room_id") Integer roomId,
                          @RequestParam("name") String name,
                          @RequestParam(value = "id",required = false) Integer id){
//        room为roomId的时候
        Res_Room resRoom = new Res_Room();
        Resource resource = new Resource();
        resource.setBrand(brand);
        resource.setId(id);
        resource.setType(type);
        resource.setName(name);
        if(resourceService.getRoomById(roomId)!=null){//根据房间id可以找到房间的话
            if(resourceService.getRoomById(roomId).getName()!=null){//房间名不为空
                resource.setRoomId(roomId);//在资源记录中添加房间id
            }
        }
        resourceService.add_res(resource);
        resRoom.setBrand(brand);
        resRoom.setId(id);
        resRoom.setType(type);
        resRoom.setName(brand+type);
        if(resource.getRoomId()!=null){
            resRoom.setRoom(resourceService.getRoomById(roomId).getName());
        }
        return Result.success();
    }

}
