package com.scheduler.csheduler.controller;

import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.SchService.ResourceService;
import com.scheduler.csheduler.bean.Res_Room_Task;
import com.scheduler.csheduler.bean.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @GetMapping("/getResources")
    public Result getResources(){
        List<Res_Room_Task> resources=resourceService.getResources();
        return Result.success().add("resource",resources);
    }
}
