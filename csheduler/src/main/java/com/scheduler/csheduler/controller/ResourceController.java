package com.scheduler.csheduler.controller;

import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.SchService.DagService;
import com.scheduler.csheduler.SchService.ResourceService;
import com.scheduler.csheduler.SchService.RoomService;
import com.scheduler.csheduler.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DagService dagService;
    @Autowired
    private RoomService roomService;
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
        if(resource.getName()!=null){
            editResource.setName(resource.getName());
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
        return Result.success();
    }

    //jinming
    @RequestMapping("/scheduler/ajax_clear")
    public Result clear(){
        List<Resource> res_list = resourceService.getResourceUsed();
        List<Dag> dag_list = dagService.getDagBound();
        List<Room> room_list = roomService.getRoomsBound();
        if (res_list.isEmpty()&&dag_list.isEmpty()&&room_list.isEmpty())
            return Result.error();
        for(Resource res:res_list)
        {
            int res_id = res.getId();
            if (resourceService.unboundRes(res_id)<=0)
            {
                return Result.error();
            }
        }
        for (Dag dag:dag_list)
        {
            int dag_id = dag.getId();
//            System.out.println("dagID:"+dag_id);
            if(dagService.unboundDag(dag_id)<=0){
                return Result.error();
            }
        }
        for (Room room:room_list)
        {
            int room_id = room.getId();
            if (roomService.unboundRoom(room_id)<=0)
            {
                return Result.error();
            }
        }
        return Result.success();
    }

    @GetMapping("/resource/queryResByRoomId")
    public Result getNameByRoomId(@RequestParam("room_id") Integer roomId){
        List<Resource> resource = resourceService.getResByRoomId(roomId);
        List<ResByRoomId> resByRoomId = new ArrayList<>();
        for(Resource tmp:resource){
            ResByRoomId rbri = new ResByRoomId();
            rbri.setId(tmp.getId());
            if(tmp.getName()!=null){
                rbri.setName(tmp.getName());
            }
            if(tmp.getType()!=null){
                rbri.setType(tmp.getType());
            }
            if(tmp.getStatus()==0){
                rbri.setStatus("offline");
            }else rbri.setStatus("online");
            resByRoomId.add(rbri);
        }
        return Result.success().add("resource",resByRoomId);
    }


    @GetMapping("/resource/queryType")
    public Result getType(){
        List<Resource> resource = resourceService.getAllResource();
        Set<String> allType = new HashSet<>();
        for(Resource tmp:resource){
            if(tmp.getType()!=null){
                allType.add(tmp.getType());
            }
        }
        return Result.success().add("resource type",allType);
    }

    //绑定资源
    @PostMapping("/resource/bindResource")
    public Result bindResource(@RequestParam("id") Integer id,
                               @RequestParam("dag_id") Integer dagId,
                               @RequestParam("task_id") Integer taskId){
        Dag dag = dagService.getDagById(dagId);
        Task task = resourceService.getTaskById(taskId);
        Resource resource = resourceService.getResourceById(id);
        Log log = new Log();
        log.setDagId(dagId);
        log.setTaskId(taskId);
        log.setResId(id);
        if(dag.getOwner()!=null){
            log.setOwner(dag.getOwner());
        }
        if(resource.getName()!=null){
            log.setResName(resource.getName());
        }
        if(task!=null){
            log.setTaskName(task.getName());
        }
        if(resource.getRoomId()!=null){
            log.setResRoom(resource.getRoomId());
        }
        if(resource.getStatus()==null||resource.getStatus()==0){
            //资源之前未被绑定，则被绑定之后将进入执行状态，资源列表也应该被更新
            log.setStatus(1);//0:等待；1:执行；2:空闲
            resourceService.bindResource(id,taskId,dagId);
        }else log.setStatus(0);//否则进入等待状态，资源列表将暂时不更新
        resourceService.updateLog(log);
        return Result.success();
    }

    //解绑资源
    @PostMapping("/resource/unbindResource")
    public Result unbindResource(@RequestParam("id") Integer id){
        Resource resource = resourceService.getResourceById(id);
        Log log = new Log();
        if(resource.getStatus()!=null&&resource.getStatus()!=0){//资源被绑定，需要解绑
            if(resource.getDagId()!=null){
                log.setDagId(resource.getDagId());
            }
            if(resource.getTaskId()!=null){
                log.setTaskId(resource.getTaskId());
            }
            log.setResId(id);
            if(dagService.getDagById(resource.getDagId())!=null){
                if(dagService.getDagById(resource.getDagId()).getOwner()!=null){
                    log.setOwner(dagService.getDagById(resource.getDagId()).getOwner());
                }
            }
            log.setStatus(2);//0:等待；1:执行；2:空闲
            if(resource.getName()!=null){
                log.setResName(resource.getName());
            }
            if(resourceService.getTaskById(resource.getTaskId())!=null){
                if(resourceService.getTaskById(resource.getTaskId()).getName()!=null){
                    log.setTaskName(resourceService.getTaskById(resource.getTaskId()).getName());
                }
            }
            if(resource.getRoomId()!=null){
                log.setResRoom(resource.getRoomId());
            }
            resourceService.updateLog(log);
            resourceService.unbindResource(id);
        }
        return Result.success();
    }

    @GetMapping("/resource/getResByType")
    public Result getResByType(@RequestParam("type") String type){
        List<Resource> resource = resourceService.getResByType(type);
        return Result.success().add("resource",resource);
    }
}
