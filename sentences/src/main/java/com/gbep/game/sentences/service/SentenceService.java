package com.gbep.game.sentences.service;

import com.gbep.game.sentences.entity.Pair;
import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.entity.UserAnswer;
import com.gbep.game.sentences.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SentenceService {
    @Autowired
    private SentenceRepo repo;

    public List<SentenceDataset> findAll() {
        return repo.findAll();
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
}
