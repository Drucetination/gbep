package com.gbep.masterservice.VO;

public enum Status {
    INITIAL("init"),
    CHOOSING_NEW_GAME_SERVICE("choosing_new_game_service"),
    CREATING_GAME_NAME("creating_name"),
    SENDING_FILE("sending_file"),
    CHOOSING_SERVICE("choosing_service"),
    CHOOSING_DATASET("choosing_dataset"),
    WAITING_GAME("waiting_game"),
    WAITING_START("waiting_start"),
    WAITING_ANSWER("waiting_answer"),
    WAITING_QUESTION("waiting_question"),
    FINISHED_GAME("finished_game");

    private final String statusCode;

    private Status(String statusCode) {
        this.statusCode = statusCode;
    }
}