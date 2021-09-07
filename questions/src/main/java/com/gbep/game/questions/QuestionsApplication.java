package com.gbep.game.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QuestionsApplication {


    public static void main(String[] args) {
        SpringApplication.run(QuestionsApplication.class, args);
        System.out.println("STARTED");
    }


}
