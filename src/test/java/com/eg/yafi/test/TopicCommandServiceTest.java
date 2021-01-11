package com.eg.yafi.test;


import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.in.CreateTopic;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.TopicCommandService;
import com.eg.yafi.service.TopicQueryService;
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
@Sql(scripts = "classpath:topic_command_service/test_create_topic.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TopicCommandServiceTest {

    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private AppUserRepo appUserRepo;

    private TopicQueryService topicQueryService;
    private TopicCommandService topicCommandService;
    private ActiveUserResolver activeUserResolver;

    private Dto2Entity dto2Entity;
    private DtoFactory dtoFactory;
    private EntityFactory entityFactory;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        this.dtoFactory = new DtoFactory();
        this.entityFactory = new EntityFactory();

        this.dto2Entity = new Dto2Entity(appUserRepo, topicRepo, activeUserResolver);
        this.topicQueryService = new TopicQueryService(topicRepo);
        this.topicCommandService = new TopicCommandService(topicRepo, dto2Entity);
    }

    @Test
    public void test_create_topic(){
        ReadTopic expected = new ReadTopic(1L, "sampleTopic", "user1");
        CreateTopic ct = new CreateTopic();
        ct.name = "sampleTopic";

        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        topicCommandService.createTopic(ct);

        ReadTopic actual = topicQueryService.readTopic(1L);

        Assert.assertEquals(expected, actual);
    }
}
