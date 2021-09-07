package com.gbep.masterservice.entity;


import com.gbep.masterservice.VO.Pair;
import org.springframework.web.client.RestTemplate;

public enum Microservice {
    NONE("none") {
        @Override
        public String getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            return " ";
        }
    },
    DEFINITIONS("DEFINITIONS") {
        @Override
        public String getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            return " ";
        }
    },
    QUESTIONS("QUESTIONS") {
        @Override
        public String getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            return " ";
        }
    },
    SENTENCES("SENTENCES") {
        @Override
        public String getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            Pair question = restTemplate.getForObject("http://SENTENCES/" + dataset + "/" + question_id, Pair.class);
            if (question == null) {
                return " ";
            }

            return question.getSentence();
        }
    };

    private final String ms;

    private Microservice(String ms) {
        this.ms = ms;
    }

    abstract public String getQuestion(RestTemplate restTemplate, String dataset, String question_id);

}