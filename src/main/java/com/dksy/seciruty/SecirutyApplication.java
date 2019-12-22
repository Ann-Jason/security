package com.dksy.seciruty;

import com.dksy.seciruty.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class SecirutyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecirutyApplication.class, args);
    }

}
