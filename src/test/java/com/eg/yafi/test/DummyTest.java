package com.eg.yafi.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DummyTest {

    @Test
    public void testy() {
        System.out.println(
                new BCryptPasswordEncoder().matches(
                        "password",
                        "$2a$10$EIXh6GJ5bqQ9h0Q9vY9o1u6Gkz8bZ3rJqvX9l1cWzqZQpQ9eQpQpO"
                )
        );

        System.out.println(new BCryptPasswordEncoder().encode("password"));
        System.out.println(new BCryptPasswordEncoder().encode("user"));
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
