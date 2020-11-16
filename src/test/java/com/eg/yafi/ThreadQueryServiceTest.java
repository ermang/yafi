package com.eg.yafi;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.out.ReadThread;
import com.eg.yafi.dto.out.ReadThreadExtended;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.ThreadQueryService;
import com.eg.yafi.util.ActiveUserResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
@Sql(scripts = "classpath:thread_query_service_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ThreadQueryServiceTest {

    @Autowired
    private  TopicRepo topicRepo;
    @Autowired
    private  AppUserRepo appUserRepo;
    @Autowired
    private  ThreadRepo threadRepo;
    @Autowired
    private AppUserThreadLikeRelRepo appUserThreadLikeRelRepo;

    private ThreadQueryService threadQueryService;
    private ActiveUserResolver activeUserResolver;

    private DtoFactory dtoFactory;
    private EntityFactory entityFactory;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        this.dtoFactory = new DtoFactory();
        this.entityFactory = new EntityFactory();

        this.threadQueryService = new ThreadQueryService(threadRepo);
    }

    @Test
    public void test_read_thread(){
        ReadThread expected = new ReadThread(1, 1, "topic1", "topic_1_content1", "user1", 0);

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        ReadThread actual = threadQueryService.readThread(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_read_threads_by_user(){
        Page<ReadThread> expected = new PageImpl<>(Arrays.asList(new ReadThread(1, 1, "topic1", "topic_1_content1", "user1", 0),
                new ReadThread(2, 1, "topic1", "topic_1_content2", "user1", 0)), TestUtil.pageable(), 2);

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        Page<ReadThread> actual = threadQueryService.readThreadsByUser(1L, TestUtil.pageable());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_read_threads_by_topic(){
        Page<ReadThreadExtended> expected = new PageImpl<>(Arrays.asList(new ReadThreadExtended(1, 1, "topic1", "topic_1_content1", "user1", 0, LocalDateTime.of(2020,01,03, 0, 0)),
                new ReadThreadExtended(2, 1, "topic1", "topic_1_content2", "user1", 0,  LocalDateTime.of(2020,01,03, 0, 0))), TestUtil.pageable(), 2);

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        Page<ReadThreadExtended> actual = threadQueryService.readThreadsByTopic(1L, TestUtil.pageable());

        Assert.assertEquals(expected, actual);
    }
}
