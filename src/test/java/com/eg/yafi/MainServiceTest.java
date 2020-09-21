package com.eg.yafi;

import com.eg.yafi.config.CustomPrincipal;
import com.eg.yafi.dto.out.ReadTopic;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.repo.AppUserThreadLikeRelRepo;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import com.eg.yafi.service.MainService;
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
public class MainServiceTest {

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
    private ActiveUserResolver activeUserResolver;

    private DtoFactory dtoFactory;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);
        this.dtoFactory = new DtoFactory();

        this.dto2Entity = new Dto2Entity(appUserRepo, topicRepo, activeUserResolver);
        this.mainService = new MainService(topicRepo, appUserRepo, threadRepo, appUserThreadLikeRelRepo, dto2Entity);
    }

    @Test
    public void test_create_user(){
       mainService.createUser(dtoFactory.createUser_V1());
    }

    @Test
    public void test_write_read_topic(){
        mainService.createUser(dtoFactory.createUser_V1());
        CustomPrincipal mockCustomPrincipal = Mockito.mock(CustomPrincipal.class);
        Mockito.when(mockCustomPrincipal.getUserId()).thenReturn(1L);
        Mockito.when(activeUserResolver.getActiveUser()).thenReturn(mockCustomPrincipal);

        mainService.createTopic(dtoFactory.createTopic_V1());
        ReadTopic result = mainService.readTopic(1L);

        Assert.assertNotNull(result);
    }
}
