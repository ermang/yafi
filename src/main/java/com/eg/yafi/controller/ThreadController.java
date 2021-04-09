package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.dto.in.UpdateThread;
import com.eg.yafi.projection.ReadThread;
import com.eg.yafi.projection.ReadThreadExtended;
import com.eg.yafi.service.ThreadCommandService;
import com.eg.yafi.service.ThreadQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/thread")
public class ThreadController {
    private final ThreadQueryService threadQueryService;
    private final ThreadCommandService threadCommandService;

    public ThreadController(ThreadQueryService threadQueryService, ThreadCommandService threadCommandService) {
        this.threadQueryService = threadQueryService;
        this.threadCommandService = threadCommandService;
    }

    @PostMapping()
    public void createThread(@RequestBody @Valid CreateThread createThread){
        threadCommandService.createThread(createThread);
    }

    @PostMapping("/like/{threadId}")
    public void likeThread(@PathVariable long threadId){
        threadCommandService.likeThread(threadId);
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
    public void updateThread(@RequestBody @Valid UpdateThread updateThread){
        threadCommandService.updateThread(updateThread);
    }

    @DeleteMapping("/{threadId}")
    public void deleteThread(@PathVariable long threadId){
        threadCommandService.deleteThread(threadId);
    }


}
