package com.gbep.masterservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private boolean finished = false;
    private String question;
}
