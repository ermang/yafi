package com.eg.yafi;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.out.ReadThread;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.MainService;
import com.eg.yafi.service.ThreadQueryService;
import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Dto2Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
public class ThreadQueryServiceTest {

    @Autowired
    private  TopicRepo topicRepo;
    @Autowired
    private  AppUserRepo appUserRepo;
    @Autowired
    private  ThreadRepo threadRepo;
    @Autowired
    private AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    private Dto2Entity dto2Entity;
    private MainService mainService;
    private ThreadQueryService threadQueryService;
    private ActiveUserResolver activeUserResolver;

    private DtoFactory dtoFactory;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        this.dtoFactory = new DtoFactory();

        this.dto2Entity = new Dto2Entity(appUserRepo, topicRepo, activeUserResolver);
        this.dto2Entity = new Dto2Entity(appUserRepo, topicRepo, activeUserResolver);
        this.mainService = new MainService(topicRepo, appUserRepo, threadRepo, appUserThreadLikeRelRepo, dto2Entity);
        this.threadQueryService = new ThreadQueryService(threadRepo);
    }

    @Test
    public void test_create_user(){
        mainService.createUser(dtoFactory.createUser_V1());
        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        mainService.createTopic(dtoFactory.createTopic_V1());
        mainService.createThread(dtoFactory.createThread_V1());

        ReadThread actual = threadQueryService.readThread(1L); //createUser(dtoFactory.createUser_V1());

        Assert.assertNotNull(actual);
    }

//    @Test
//    public void test_write_read_topic(){
//        threadQueryService.createUser(dtoFactory.createUser_V1());
//        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
//        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
//        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);
//
//        threadQueryService.createTopic(dtoFactory.createTopic_V1());
//        ReadTopic result = threadQueryService.readTopic(1L);
//
//        Assert.assertNotNull(result);
//    }
}
