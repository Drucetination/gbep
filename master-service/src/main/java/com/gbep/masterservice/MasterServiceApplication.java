package com.gbep.masterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MasterServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(MasterServiceApplication.class, args);

    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
