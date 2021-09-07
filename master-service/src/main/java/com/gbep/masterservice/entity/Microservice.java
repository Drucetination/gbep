package com.gbep.masterservice.entity;


public enum Microservice {
    NONE("none"),
    DEFINITIONS("DEFINITIONS"),
    QUESTIONS("QUESTIONS"),
    SENTENCES("SENTENCES");

    private final String ms;

    private Microservice(String ms) {
        this.ms = ms;
    }
}