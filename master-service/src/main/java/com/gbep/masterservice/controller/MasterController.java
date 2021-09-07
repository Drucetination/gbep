package com.gbep.masterservice.controller;


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

    @GetMapping("/{ms_name}")
    public List<String> chooseGame(@PathVariable String ms_name) {
        return masterService.chooseGame(ms_name);
    }

    @PostMapping("/new_game/{dataset_name}/{user_id}")
    public boolean createNewGame(@PathVariable("dataset_name") String name, @PathVariable("user_id") String user_id) {
        return masterService.createNewGame(name, user_id);
    }

    
}
