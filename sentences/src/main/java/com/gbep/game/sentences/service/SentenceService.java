package com.gbep.game.sentences.service;

import com.gbep.game.sentences.entity.Pair;
import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.entity.UserAnswer;
import com.gbep.game.sentences.repository.SentenceRepo;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SentenceService {
    @Autowired
    private SentenceRepo repo;

    public List<String> findAll() {
        MongoClient mongoClient = MongoClients
                                        .create(
                                                new ConnectionString("mongodb://rootuser:rootpass@localhost/?authSource=sentences")
                                        );
        MongoDatabase db = mongoClient.getDatabase("sentences");
        MongoCollection<Document> collection = db.getCollection("sentenceDataset");
        return collection.distinct("name", String.class).into(new ArrayList<String>());
    }

    public Optional<SentenceDataset> saveDataset(SentenceDataset dataset) {
        Optional<SentenceDataset> opt = repo.findSentenceDatasetByName(dataset.getName());
        if (opt.isEmpty()) {
            return Optional.empty();
        } else {
            repo.save(dataset);
            return opt;
        }
    }

    public List<String> getQuestionList(String name) {
        Optional<SentenceDataset> data = repo.findSentenceDatasetByName(name);
        List<String> response = new ArrayList<>();
        if (data.isPresent()) {
            for (Pair question : data.get().getData()) {
                response.add(question.getId());
            }
        }

        return response;
    }

    public boolean compareAnswers(String name, String question_id, UserAnswer user_answer) {
        Optional<SentenceDataset> data = repo.findSentenceDatasetByName(name);
        boolean isCorrect = false;
        if (data.isPresent()) {
            for (Pair question : data.get().getData()) {
                if (Objects.equals(question.getId(), question_id)) {
                    isCorrect = Objects.equals(question.getWord(), user_answer.getUser_answer());
                    break;
                }
            }
        }

        return isCorrect;
    }

    public Optional<SentenceDataset> deleteDatasetByName(String name) {
        return repo.deleteSentenceDatasetByName(name);
    }

    public Pair getQuestion(String id, String name) {
        Optional<SentenceDataset> data = repo.findSentenceDatasetByName(name);
        if (data.isPresent()) {
            for (Pair question : data.get().getData()) {
                if (Objects.equals(question.getId(), id)) {
                    return question;
                }
            }
        }

        return new Pair();
    }

    public Boolean checkIfExists(String name) {
        Optional<SentenceDataset> dt = repo.findSentenceDatasetByName(name);
        return dt.isEmpty();
    }
}
