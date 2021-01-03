package com.eg.yafi.test;


import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.TopicQueryService;
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

import java.util.Arrays;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
@Sql(scripts = "classpath:thread_query_service_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

public class TopicQueryServiceTest {

    @Autowired
    private TopicRepo topicRepo;

    private TopicQueryService topicQueryService;
    private ActiveUserResolver activeUserResolver;

    private DtoFactory dtoFactory;
    private EntityFactory entityFactory;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        this.dtoFactory = new DtoFactory();
        this.entityFactory = new EntityFactory();

        this.topicQueryService = new TopicQueryService(topicRepo);
    }

    @Test
    public void test_search_topic_by_name(){
        Page<ReadTopic> expected = new PageImpl<>(Arrays.asList(
                new ReadTopic(2, "topic2", "user1")
        ), TestUtil.pageable(), 1);

        Page<ReadTopic> actual = topicQueryService.searchTopicByName("2", TestUtil.pageable());

        Assert.assertEquals(expected, actual);
    }

}
