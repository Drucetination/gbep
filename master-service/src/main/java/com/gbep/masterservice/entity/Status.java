package com.gbep.masterservice.entity;

public enum Status {
    INITIAL("init"),
    WAITING_GAME("waiting_game"),
    WAITING_ANSWER("waiting_answer"),
    WAITING_QUESTION("waiting_question"),
    FINISHED_GAME("finished_game");

    private final String statusCode;

    private Status(String statusCode) {
        this.statusCode = statusCode;
    }
}