package com.gbep.botapi.bot.model;


import org.springframework.web.client.RestTemplate;

public enum Microservice {
    NONE("NONE"),
    DEFINITIONS("DEFINITIONS"),
    QUESTIONS("QUESTIONS"),
    SENTENCES("SENTENCES");

    private final String ms;

    private Microservice(String ms) {
        this.ms = ms;
    }

}