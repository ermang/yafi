package com.eg.yafi.controller;

import com.eg.yafi.dto.CreateTopic;
import com.eg.yafi.dto.ReadTopic;
import com.eg.yafi.service.MainService;
import com.eg.yafi.util.ActiveUserResolver;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final MainService mainService;

    public TopicController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping()
    public void createTopic(@RequestBody CreateTopic createTopic){
        ActiveUserResolver activeUserResolver = new ActiveUserResolver();
        //UserDetails details = activeUserResolver.getActiveUser();
        mainService.createTopic(createTopic);
    }

    @GetMapping("/{topicId}")
    public ReadTopic readTopic(@PathVariable long topicId){

        ReadTopic rt = mainService.readTopic(topicId);

        return rt;
    }
}
