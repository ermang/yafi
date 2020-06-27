package com.eg.yafi;

import com.eg.yafi.dto.ReadTopic;
import com.eg.yafi.service.MainService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@WithMockUser   //DefaultUser with username "user", password "password", and a single GrantedAuthority named "ROLE_USER"
@DataJpaTest(includeFilters = @ComponentScan.Filter(classes = {Service.class}))
public class MainServiceTest {

    @Autowired
    MainService mainService;

    @Test
    public void test_read_topic(){
        ReadTopic result = mainService.readTopic(1L);

        Assert.assertNotNull(result);
    }
}
