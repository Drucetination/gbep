package com.gbep.masterservice.controller;


import com.gbep.masterservice.entity.QuestionResponse;
import com.gbep.masterservice.VO.UserConfig;
import com.gbep.masterservice.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/service/put/{user_id}/{path}")
    public Boolean putJson(@PathVariable String path, @PathVariable String user_id) {
        String full_path = "../bot-api/data/" + path;
        return masterService.putJson(full_path, user_id);
    }


    @GetMapping("/config/{user_id}")
    public UserConfig getConig(@PathVariable String user_id) {
        return masterService.getConfig(user_id);
    }

    @GetMapping("/status/{user_id}/{status}")
    public Boolean changeInitStatus(@PathVariable String user_id, @PathVariable String status) {
        return masterService.changeInitStatus(user_id, status);
    }

    @GetMapping("/{user_id}/new_game/{service}/{name}")
    public Boolean findByName(@PathVariable String service, @PathVariable String user_id, @PathVariable String name) {
        return masterService.findByName(user_id, service, name);
    }

    @GetMapping("/service/change/{user_id}/{service}")
    public Boolean changeService(@PathVariable String user_id, @PathVariable String service) {
        return masterService.changeService(user_id, service);
    }

    @GetMapping("/users/{user_id}")
    public boolean registerUser(@PathVariable String user_id) {
        return masterService.registerUser(user_id);
    }

    @GetMapping("/service/{user_id}/{ms_name}")
    public List<String> getListOfDatasets(@PathVariable String ms_name, @PathVariable String user_id) {
        return masterService.getListOfDatasets(ms_name, user_id);
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

    @GetMapping("/{user_id}/answer/{answer}")
    public Boolean compareAnswer(@PathVariable String answer, @PathVariable String user_id) {
        return masterService.compareAnswer(user_id, answer);
    }

    @GetMapping("/{user_id}/results/")
    public boolean getResults(@PathVariable String user_id) {
        return masterService.getResults(user_id);
    }
}
