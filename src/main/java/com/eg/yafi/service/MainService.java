package com.eg.yafi.service;

import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.dto.out.ReadPopularTopics;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.entity.AppUser;
import com.eg.yafi.entity.AppUserThreadLikeRel;
import com.eg.yafi.entity.Thread;
import com.eg.yafi.entity.Topic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MainService {
    private final TopicRepo topicRepo;
    private final AppUserRepo appUserRepo;
    private final ThreadRepo threadRepo;
    private final Dto2Entity dto2Entity;
    private final AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    public MainService(TopicRepo topicRepo, AppUserRepo appUserRepo, ThreadRepo threadRepo,
                       AppUserThreadLikeRelRepo appUserThreadLikeRelRepo, Dto2Entity dto2Entity) {
        this.topicRepo = topicRepo;
        this.appUserRepo = appUserRepo;
        this.threadRepo = threadRepo;
        this.appUserThreadLikeRelRepo = appUserThreadLikeRelRepo;
        this.dto2Entity = dto2Entity;
    }

    public void createTopic(CreateTopic createTopic) {
        Topic t = dto2Entity.createTopic2Topic(createTopic);

        topicRepo.save(t);
    }

    public void createUser(CreateUser createUser) {
        AppUser appUser = dto2Entity.createUser2AppUser(createUser);

        appUserRepo.save(appUser);
    }

    public void createThread(CreateThread createThread) {
        Thread t = dto2Entity.createThread2Thread(createThread);

        threadRepo.save(t);
    }

    public void likeThread(long threadId) {
        Thread t = threadRepo.getOne(threadId);
        t.setLikeCount(t.getLikeCount() + 1);

        threadRepo.save(t);

        AppUserThreadLikeRel likeRel = new AppUserThreadLikeRel();
        likeRel.setThread(t);
        likeRel.setAppUser(t.getAppUser());

        appUserThreadLikeRelRepo.save(likeRel);
    }

    public ReadTopic readTopic(long topicId) {
        ReadTopic rt = topicRepo.findTopicByIdRO(topicId);

        return rt;
    }

    public ReadPopularTopics readPopularTopics() {
        List<Object[]> oList = topicRepo.findPopularTopicsRO();

        List<ReadTopic> readTopics = new ArrayList<>();

        for(Object[] o : oList){
          ReadTopic rt = new ReadTopic(((BigInteger)o[0]).longValue(), (String)o[1], (String)o[2]);
          readTopics.add(rt);
        }

        return new ReadPopularTopics(readTopics);
    }
}
