package com.eg.yafi.test;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.in.CreateThread;
import com.eg.yafi.dto.in.UpdateThread;
import com.eg.yafi.dto.out.ReadThread;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.ThreadCommandService;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
@Sql(scripts = "classpath:thread_command_service_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ThreadCommandServiceTest {

    @Autowired
    private  TopicRepo topicRepo;
    @Autowired
    private  AppUserRepo appUserRepo;
    @Autowired
    private  ThreadRepo threadRepo;
    @Autowired
    private AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    private Dto2Entity dto2Entity;
    private ThreadCommandService threadCommandService;
    private ThreadQueryService threadQueryService;
    private ActiveUserResolver activeUserResolver;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);

        this.dto2Entity = new Dto2Entity(appUserRepo, topicRepo, activeUserResolver);
        this.threadCommandService = new ThreadCommandService(dto2Entity, threadRepo, appUserThreadLikeRelRepo, activeUserResolver);
        this.threadQueryService = new ThreadQueryService(threadRepo);
    }

    @Test
    public void test_create_thread(){
        ReadThread expected = new ReadThread(3, 1, "topic1", "topic_1_content3", "user1", 0);

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        CreateThread createThread = new CreateThread();
        createThread.topicId = 1L;
        createThread.content = "topic_1_content3";
        threadCommandService.createThread(createThread);
        ReadThread actual = threadQueryService.readThread(3L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_like_thread(){
        ReadThread expected = new ReadThread(2, 1, "topic1", "topic_1_content2", "user2", 1);

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        threadCommandService.likeThread(2);
        ReadThread actual = threadQueryService.readThread(2L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_update_thread(){
        ReadThread expected = new ReadThread(1, 1, "topic1", "content_updated", "user1", 0);
        UpdateThread ut = new UpdateThread();
        ut.id = 1L;
        ut.content = "content_updated";

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        threadCommandService.updateThread(ut);
        ReadThread actual = threadQueryService.readThread(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_delete_thread(){
        ReadThread expected = new ReadThread(1, 1, "topic1", "content_updated", "user1", 0);


        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        threadCommandService.deleteThread(1L);
        ReadThread actual = threadQueryService.readThread(1L);

        Assert.assertNull(actual);
    }


}
