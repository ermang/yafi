package com.eg.yafi.controller;

import com.eg.yafi.req.CreateThreadReq;
import com.eg.yafi.req.UpdateThreadReq;
import com.eg.yafi.projection.ReadThread;
import com.eg.yafi.projection.ReadThreadExtended;
import com.eg.yafi.service.ThreadCommandService;
import com.eg.yafi.service.ThreadQueryService;
import com.eg.yafi.servicereq.CreateThreadServiceReq;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import com.eg.yafi.servicereq.LikeThreadServiceReq;
import com.eg.yafi.util.Req2ServiceReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    private final Req2ServiceReq req2ServiceReq;
    private final ThreadQueryService threadQueryService;
    private final ThreadCommandService threadCommandService;

    public ThreadController(Req2ServiceReq req2ServiceReq, ThreadQueryService threadQueryService, ThreadCommandService threadCommandService) {
        this.req2ServiceReq = req2ServiceReq;
        this.threadQueryService = threadQueryService;
        this.threadCommandService = threadCommandService;
    }

    @PostMapping()
    public void createThread(@RequestBody @Valid CreateThreadReq createThreadReq){
        CreateThreadServiceReq serviceReq = req2ServiceReq.createThreadReq2CreateThreadServiceReq(createThreadReq);
        threadCommandService.createThread(serviceReq);
    }

    @PostMapping("/like/{threadId}")
    public void likeThread(@PathVariable long threadId){
        LikeThreadServiceReq serviceReq = req2ServiceReq.likeTread2LikeThreadServiceReq(threadId);
        threadCommandService.likeThread(serviceReq);
    }

    @GetMapping("/{threadId}")
    public ReadThread readThread(@PathVariable long threadId){
        ReadThread readThread = threadQueryService.readThread(threadId);

        return readThread;
    }

    @GetMapping("/topic/{topicId}")
    public Page<ReadThreadExtended> readThreadsByTopicId(@PathVariable long topicId, Pageable pageable){
        Page<ReadThreadExtended> readThread = threadQueryService.readThreadsByTopic(topicId, pageable);

        return readThread;
    }

    @GetMapping("/user/{userId}")
    public Page<ReadThread> readThreadsByUser(@PathVariable long userId, Pageable pageable){
        Page<ReadThread> readThread = threadQueryService.readThreadsByUser(userId, pageable);

        return readThread;
    }

    @GetMapping("/top-liked")
    public Page<ReadThread> readMostLikedThreads(Pageable pageable){

        Page<ReadThread> readThread = threadQueryService.readMostLikedThreads(pageable);

        return readThread;
    }

    @GetMapping("/recent")
    public Page<ReadThreadExtended> readRecentThreads(Pageable pageable){

        Page<ReadThreadExtended> readThread = threadQueryService.readRecentThreads(pageable);

        return readThread;
    }

    @PutMapping()
    public void updateThread(@RequestBody @Valid UpdateThreadReq updateThreadReq){
        threadCommandService.updateThread(updateThreadReq);
    }

    @DeleteMapping("/{threadId}")
    public void deleteThread(@PathVariable long threadId){
        threadCommandService.deleteThread(threadId);
    }


}
