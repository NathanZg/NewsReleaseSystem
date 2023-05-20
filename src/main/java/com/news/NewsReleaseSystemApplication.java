package com.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author Sancean
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class NewsReleaseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsReleaseSystemApplication.class, args);
    }

}
