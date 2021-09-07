package com.gbep.masterservice.controller;


import com.gbep.masterservice.entity.QuestionResponse;
import com.gbep.masterservice.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MasterController {

    @Autowired
    private MasterService masterService;


    @GetMapping("/users/{user_id}")
    public boolean registerUser(@PathVariable String user_id) {
        return masterService.registerUser(user_id);
    }

    @GetMapping("/{ms_name}/")
    public List<String> getListOfDatasets(@PathVariable String ms_name) {
        return masterService.getListOfDatasets(ms_name);
    }

    @GetMapping("/{user_id}/new_game/{dataset_name}/")
    public boolean createNewGame(@PathVariable("dataset_name") String name, @PathVariable("user_id") String user_id) {
        return masterService.createNewGame(name, user_id);
    }

    @GetMapping("/{user_id}/start/")
    public QuestionResponse startGame(@PathVariable("user_id") String user_id) {
        return masterService.getQuestion(user_id);
    }

    @GetMapping("/{user_id}/play/")
    public QuestionResponse getQuestion(@PathVariable("user_id") String user_id) {
        return masterService.getQuestion(user_id);
    }

    @GetMapping("/{user_id}/results/")
    public boolean getResults(@PathVariable String user_id) {
        return masterService.getResults(user_id);
    }
}
