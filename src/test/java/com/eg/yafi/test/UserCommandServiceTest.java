package com.eg.yafi.test;

import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.service.UserCommandService;
import com.eg.yafi.util.ActiveUserResolver;
import com.eg.yafi.util.Dto2Entity;
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
//@Sql(scripts = "classpath:thread_query_service_test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserCommandServiceTest {

    @Autowired
    private AppUserRepo appUserRepo;

    private UserCommandService userCommandService;
    private ActiveUserResolver activeUserResolver;

    private Dto2Entity dto2Entity;

    @Before
    public void setup() {
        this.activeUserResolver = Mockito.mock(ActiveUserResolver.class);

        this.dto2Entity = new Dto2Entity(appUserRepo, null, activeUserResolver);
        this.userCommandService = new UserCommandService(appUserRepo, dto2Entity);
    }

    @Test
    public void test_create_user(){
        CreateUser createUser = new CreateUser();
        createUser.username = "user1";
        createUser.password = "password";

        userCommandService.createUser(createUser);
    }
}
