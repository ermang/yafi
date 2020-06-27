package com.eg.yafi.controller;

import com.eg.yafi.dto.CreateThread;
import com.eg.yafi.dto.ReadThread;
import com.eg.yafi.service.MainService;
import com.eg.yafi.service.ThreadQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thread")
public class ThreadController {
    private final MainService mainService;
    private final ThreadQueryService threadQueryService;

    public ThreadController(MainService mainService, ThreadQueryService threadQueryService) {
        this.mainService = mainService;
        this.threadQueryService = threadQueryService;
    }

    @PostMapping()
    public void createThread(@RequestBody CreateThread createThread){
        mainService.createThread(createThread);
    }

    @PostMapping("{threadId}/like")
    public void likeThread(@PathVariable long threadId){
        mainService.likeThread(threadId);
    }

    @GetMapping("/{threadId}")
    public ReadThread readThread(@PathVariable long threadId){
        ReadThread readThread = threadQueryService.readThread(threadId);

        return readThread;
    }

    @GetMapping("/topic/{topicId}")
    public Page<ReadThread> readThreadsByTopic(@PathVariable long topicId, Pageable pageable){
        Page<ReadThread> readThread = threadQueryService.readThreadsByTopic(topicId, pageable);

        return readThread;
    }

    @GetMapping("/user/{userId}")
    public Page<ReadThread> readThreadsByUser(@PathVariable long userId, Pageable pageable){
        Page<ReadThread> readThread = threadQueryService.readThreadsByUser(userId, pageable);

        return readThread;
    }
}
