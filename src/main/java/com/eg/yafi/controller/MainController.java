package com.eg.yafi.controller;


import com.eg.yafi.service.ThreadQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final ThreadQueryService threadQueryService;

    public MainController( ThreadQueryService threadQueryService) {

        this.threadQueryService = threadQueryService;
    }

    @GetMapping("/")
    public String greeting() {
        return "Welcome to YAFI";
    }

//    @GetMapping("/popi")
//    public void readPopiTopic(LocalDate localDate){
//        mainS
//    }

}
