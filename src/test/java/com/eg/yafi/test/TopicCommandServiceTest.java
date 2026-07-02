package com.eg.yafi.test;


import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.TopicCommandService;
import com.eg.yafi.service.TopicQueryService;
import com.eg.yafi.servicereq.CreateTopicServiceReq;
import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Dto2Entity;
import com.eg.yafi.util.ServiceReq2Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@SpringBootTest//(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
@Sql(scripts = "classpath:topic_command_service/test_create_topic.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TopicCommandServiceTest {

    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private AppUserRepo appUserRepo;

    private TopicQueryService topicQueryService;
    private TopicCommandService topicCommandService;
    private ActiveUserResolver activeUserResolver;


    //private DtoFactory dtoFactory;
    private EntityFactory entityFactory;
    @Autowired
    private ServiceReq2Entity serviceReq2Entity;
    @Autowired
    private ThreadRepo threadRepo;

    @BeforeEach
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        //this.dtoFactory = new DtoFactory();
        this.entityFactory = new EntityFactory();

        this.topicQueryService = new TopicQueryService(topicRepo, threadRepo);
        this.topicCommandService = new TopicCommandService(appUserRepo, topicRepo, serviceReq2Entity);
    }

//    @Test
//    public void test_create_topic(){
//        ReadTopic expected = new ReadTopic(1L, "sampleTopic", "user1");
//        CreateTopicServiceReq ctsr = new CreateTopicServiceReq();
//        ctsr.name = "sampleTopic";
//        ctsr.userId = 1L;
//
//        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
//        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
//        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);
//
//        topicCommandService.createTopic(ctsr);
//
//        ReadTopic actual = topicQueryService.readTopic(1L);
//
//        Assertions.assertEquals(expected, actual);
//    }

    @Test
    public void gulugulu() {
        CreateTopicServiceReq ctsr = new CreateTopicServiceReq();
        ctsr.name = "sampleTopic";
        ctsr.userId = 1L;

        topicCommandService.createTopic(ctsr);
    }
}
