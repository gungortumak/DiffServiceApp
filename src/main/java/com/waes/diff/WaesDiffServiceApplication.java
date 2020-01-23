package com.waes.diff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *
 */
@SpringBootApplication
@EnableCaching
public class WaesDiffServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaesDiffServiceApplication.class, args);
    }

}

