package com.gbep.masterservice.VO;


import com.gbep.masterservice.VO.Pair;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Paths;

public enum Microservice {
    NONE("NONE") {
        @Override
        public Pair getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            return new Pair();
        }

        @Override
        public Boolean postDataset(RestTemplate restTemplate, String full_path) {
            return null;
        }
    },
    DEFINITIONS("DEFINITIONS") {
        @Override
        public Pair getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            Task task = restTemplate.getForObject("http://DEFINITIONS/" + dataset + "/" + question_id, Task.class);
            Pair pair = new Pair();
            assert task != null;
            pair.setSentence(task.getDefinition());
            pair.setWord(task.getWord());
            return pair;
        }

        @Override
        public Boolean postDataset(RestTemplate restTemplate, String full_path) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(full_path)));
                Game data = new Gson().fromJson(json, Game.class);
                restTemplate.postForObject("http://DEFINITIONS/", data, Game.class);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    },
    QUESTIONS("QUESTIONS") {
        @Override
        public Pair getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            Question question = restTemplate.getForObject("http://QUESTIONS/" + dataset + "/" + question_id, Question.class);
            Pair pair = new Pair();
            assert question != null;
            pair.setWord(question.getCorrectAnswer());
            pair.setSentence(question.getThequestion() + "\n" +
                    question.getWrongAnswerThree() + "\n" +
                    question.getCorrectAnswer() + "\n" + question.getWrongAnswerOne() + "\n" + question.getWrongAnswerTwo());
            return pair;
        }

        @Override
        public Boolean postDataset(RestTemplate restTemplate, String full_path) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(full_path)));
                QuestionDataset data = new Gson().fromJson(json, QuestionDataset.class);
                restTemplate.postForObject("http://QUESTIONS/", data, QuestionDataset.class);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    },
    SENTENCES("SENTENCES") {
        @Override
        public Pair getQuestion(RestTemplate restTemplate, String dataset, String question_id) {
            return restTemplate.getForObject("http://SENTENCES/" + dataset + "/" + question_id, Pair.class);
        }

        @Override
        public Boolean postDataset(RestTemplate restTemplate, String full_path) {
            try {
                String json = new String(Files.readAllBytes(Paths.get(full_path)));
                SentenceDataset data = new Gson().fromJson(json, SentenceDataset.class);
                restTemplate.postForObject("http://SENTENCES/", data, SentenceDataset.class);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    };

    private final String ms;

    private Microservice(String ms) {
        this.ms = ms;
    }

    abstract public Pair getQuestion(RestTemplate restTemplate, String dataset, String question_id);

    abstract public Boolean postDataset(RestTemplate restTemplate, String full_path);
}