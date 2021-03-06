package com.eg.yafi.test;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class TestUtil {

    public static Pageable pageable() {
        Pageable p = PageRequest.of(0, 20, Sort.unsorted());

        return p;
    }

    public static Pageable pageable(int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.unsorted());

        return p;
    }

//    @Test
//    public void test_bcrypt() {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        Assert.assertEquals("$2a$10$sohkYjFoQ/4Xum78.AXBaeW.0fbSbh1u1041uHycloUMrxvpYC8I.", encoder.encode("user1"));
//    }

}
