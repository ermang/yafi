package com.eg.yafi.util;

import com.eg.yafi.req.CreateThreadReq;
import com.eg.yafi.req.CreateTopicReq;
import com.eg.yafi.req.CreateUserReq;
import com.eg.yafi.servicereq.CreateThreadServiceReq;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import com.eg.yafi.servicereq.CreateUserServiceReq;
import com.eg.yafi.servicereq.LikeThreadServiceReq;
import org.springframework.stereotype.Component;

@Component
public class Req2ServiceReq {

    private final ActiveUserResolver activeUserResolver;

    public Req2ServiceReq(ActiveUserResolver activeUserResolver) {
        this.activeUserResolver = activeUserResolver;
    }

    public CreateTopicServiceReq createTopicReq2CreateTopicServiceReq(CreateTopicReq createTopicReq) {
        CreateTopicServiceReq serviceReq = new CreateTopicServiceReq();
        serviceReq.name = createTopicReq.name;
        serviceReq.userId = activeUserResolver.getActiveUser().getUserId();

        return serviceReq;
    }

    public CreateThreadServiceReq createThreadReq2CreateThreadServiceReq(CreateThreadReq createThreadReq) {
        CreateThreadServiceReq serviceReq = new CreateThreadServiceReq();
        serviceReq.content = createThreadReq.content;
        serviceReq.topicId = createThreadReq.topicId;
        serviceReq.userId = activeUserResolver.getActiveUser().getUserId();

        return serviceReq;
    }

    public CreateUserServiceReq createUserReq2CreateUserServiceReq(CreateUserReq createUserReq) {
        CreateUserServiceReq serviceReq = new CreateUserServiceReq();
        serviceReq.username = createUserReq.username;
        serviceReq.password = createUserReq.password;

        return serviceReq;
    }

    public LikeThreadServiceReq likeTread2LikeThreadServiceReq(long threadId) {
        LikeThreadServiceReq serviceReq = new LikeThreadServiceReq();
        serviceReq.threadId = threadId;
        serviceReq.userId = activeUserResolver.getActiveUser().getUserId();

        return serviceReq;
    }
}
