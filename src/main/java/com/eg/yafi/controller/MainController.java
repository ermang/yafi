package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.service.MainService;
import com.eg.yafi.service.ThreadQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final MainService mainService;
    private final ThreadQueryService threadQueryService;

    public MainController(MainService mainService, ThreadQueryService threadQueryService) {
        this.mainService = mainService;
        this.threadQueryService = threadQueryService;
    }

    @GetMapping("/")
    public String greeting() {
        return "Welcome to YAFI";
    }

    @PostMapping("/user")
    public void createUser(@RequestBody CreateUser createUser){
        mainService.createUser(createUser);
    }

    @PostMapping("/login")
    public void login(){
        return;
    }

//    @GetMapping("/popi")
//    public void readPopiTopic(LocalDate localDate){
//        mainS
//    }

}
