package com.gbep.game.questions.controllers;

import com.gbep.game.questions.model.Answer;
import com.gbep.game.questions.model.Question;
import com.gbep.game.questions.model.QuestionsDataset;
import com.gbep.game.questions.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @PostMapping("/save")
    QuestionsDataset saveDataset(@RequestBody QuestionsDataset dataset){
        int start = 0;
        for (Question question : dataset.getQuestions()) {
            question.setQuestion_id(String.valueOf(start));
        }
        return  questionsService.saveDataset(dataset);

    }

//    @GetMapping("/{name}")
//    List<String> getQuestions(@PathVariable("name") String datasetName){
//
//        return questionsService.getQuestionsFromDataset(datasetName);
//
//    }


    @PostMapping("/{name}/{id}")
    Boolean getResult(@PathVariable("name") String datasetName, @PathVariable("id") String questionID, @RequestBody Answer answer){

        return  questionsService.getResult(datasetName,questionID,answer);

    }

    @DeleteMapping("/delete/{name}")
    void deleteDataset(@PathVariable("name") String datasetName){

        questionsService.deleteDataset(datasetName);

    }
    
    @GetMapping("/all")
    List<String> getAllDatasetNames(){

        return questionsService.getAllDatasetNames();

    }

    @GetMapping("/check_name/{name}")
    public Boolean checkName(@PathVariable String name) {
        return questionsService.checkIfExists(name);
    }

    @GetMapping("/{name}/{question_id}")
    public Question getQuestion(@PathVariable String name, String question_id) {
        return questionsService.getQuestion(name, question_id);
    }


    @GetMapping("/{name}")
    public List<String> findGameById(@PathVariable String name) {
        return questionsService.findGameByName(name);
    }

}
