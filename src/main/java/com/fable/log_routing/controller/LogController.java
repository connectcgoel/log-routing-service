package com.fable.log_routing.controller;

import com.fable.log_routing.entity.Log;
import com.fable.log_routing.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/log")
    public void receiveLog(@RequestBody Log log) {
        logService.saveLog(log);
    }
}