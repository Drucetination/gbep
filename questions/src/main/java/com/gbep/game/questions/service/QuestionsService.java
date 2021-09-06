package com.gbep.game.questions.service;


import com.gbep.game.questions.model.Answer;
import com.gbep.game.questions.model.Question;
import com.gbep.game.questions.model.QuestionsDataset;
import com.gbep.game.questions.repository.DatasetRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class QuestionsService {

    @Autowired
    DatasetRepository datasetRepository;

    public QuestionsDataset saveDataset(QuestionsDataset dataset) {
        return datasetRepository.save(dataset);
    }

    public List<QuestionsDataset> findAllDatasets(){

        return datasetRepository.findAll();

    }

    public QuestionsDataset getDatasetByName(String datasetName){

        return findAllDatasets().stream()
                .filter(dataset -> dataset.getName().equals(datasetName))
                .findAny().orElse(null);
    }

    public List<String> getQuestionsFromDataset(String datasetName) {

        return getDatasetByName(datasetName)
                .getQuestions().stream().map(Question::getThequestion)
                .collect(Collectors.toList());
    }

    public Boolean getResult(String datasetName, String questionID, Answer answer) {

        return getDatasetByName(datasetName).getQuestions().stream()
                .filter(question -> question.getId().equals(questionID))
                .anyMatch(question -> question.getCorrectAnswer().equals(answer.getUserAnswer()));

    }

    public void deleteDataset(String datasetName) {
        datasetRepository.deleteById(getDatasetByName(datasetName).getId());
    }
}
