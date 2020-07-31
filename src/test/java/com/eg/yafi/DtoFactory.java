package com.eg.yafi;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.CreateThread;
import com.eg.yafi.dto.CreateTopic;
import com.eg.yafi.dto.CreateUser;
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
}