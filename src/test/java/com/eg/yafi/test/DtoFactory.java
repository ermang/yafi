package com.eg.yafi.test;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.projection.ReadThread;
import com.eg.yafi.util.Constant;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class DtoFactory {

    public CreateUser createUser_V1() {
        CreateUser createUser = new CreateUser();
        createUser.username = "user";
        createUser.password = "password";

        return createUser;
    }

    public CreateTopic createTopic_V1() {
        CreateTopic ct = new CreateTopic();
        ct.name = "topic1";

        return ct;
    }

    public CustomPrincipal customPrincipal_V1() {
        CustomPrincipal cp = new CustomPrincipal("user","", true, true,
                true, true,
                Collections.singletonList(new SimpleGrantedAuthority(Constant.ROLE_USER)));

        return cp;
    }

    public CreateThread createThread_V1() {
        CreateThread ct = new CreateThread();
        ct.topicId = 1L;
        ct.content = "content1";

        return ct;
    }

    public ReadThread readThread_v1() {
        ReadThread rt = new ReadThread(1, 1, "topic1", "topic_1_content1", "user", 0);

        return rt;
    }
}
