package com.gbep.game.questions.controllers;

import com.gbep.game.questions.model.Answer;
import com.gbep.game.questions.model.QuestionsDataset;
import com.gbep.game.questions.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @PostMapping("/save")
    QuestionsDataset saveDataset(@RequestBody QuestionsDataset dataset){

        return  questionsService.saveDataset(dataset);

    }

    @GetMapping("/{name}")
    List<String> getQuestions(@PathVariable("name") String datasetName){

        return questionsService.getQuestionsFromDataset(datasetName);

    }


    @PostMapping("/{name}/{id}")
    Boolean getResult(@PathVariable("name") String datasetName, @PathVariable("id") String questionID, @RequestBody Answer answer){

        return  questionsService.getResult(datasetName,questionID,answer);

    }

    @DeleteMapping("/delete/{name}")
    void deleteDataset(@PathVariable("name") String datasetName){

        questionsService.deleteDataset(datasetName);

    }

}
