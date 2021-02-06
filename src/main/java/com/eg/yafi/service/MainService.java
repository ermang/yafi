package com.eg.yafi.service;

import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MainService {
    private final TopicRepo topicRepo;
    private final AppUserRepo appUserRepo;
    private final Dto2Entity dto2Entity;

    public MainService(TopicRepo topicRepo, AppUserRepo appUserRepo, Dto2Entity dto2Entity) {
        this.topicRepo = topicRepo;
        this.appUserRepo = appUserRepo;

        this.dto2Entity = dto2Entity;
    }



}
