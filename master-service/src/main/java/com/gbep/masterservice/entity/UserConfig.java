package com.gbep.masterservice.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

enum Status {
    INITIAL,
    WAITING_GAME,
    WAITING_ANSWER,
    WAITING_QUESTION,
    FINISHED_GAME
}

enum MicroService {
    NONE,
    DEFINITIONS,
    QUESTIONS,
    SENTENCES
}

@Document
public class UserConfig {
    @Id
    private String user_id;
    private Status status = Status.INITIAL;
    private MicroService service = MicroService.NONE;
    private Optional<String> dataset_name;
    private int next_question;
    private List<String> questions;

}
