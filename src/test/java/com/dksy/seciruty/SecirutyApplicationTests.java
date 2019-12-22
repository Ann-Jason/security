package com.dksy.seciruty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SecirutyApplicationTests {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void contextLoads() {
        System.out.println(encoder.encode("123456"));
    }

}
