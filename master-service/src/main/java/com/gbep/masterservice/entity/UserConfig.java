package com.gbep.masterservice.entity;


import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Document
@AllArgsConstructor
public class UserConfig {
    @Id
    private String user_id;
    private Status status = Status.INITIAL;
    private Microservice service = Microservice.NONE;
    private String dataset_name;
    private int next_question = 0;
    private List<String> questions = new ArrayList<String>();
    private int correct_answered = 0;
    private boolean wonLast = false;

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public int getNext_question() {
        return next_question;
    }

    public void setNext_question(int next_question) {
        this.next_question = next_question;
    }

    public String getDataset_name() {
        return dataset_name;
    }

    public void setDataset_name(String dataset_name) {
        this.dataset_name = dataset_name;
    }

    public Microservice getService() {
        return service;
    }

    public void setService(Microservice service) {
        this.service = service;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCorrect_answered() {
        return correct_answered;
    }

    public void setCorrect_answered(int correct_answered) {
        this.correct_answered = correct_answered;
    }

    public boolean isWonLast() {
        return wonLast;
    }

    public void setWonLast(boolean wonLast) {
        this.wonLast = wonLast;
    }
}
