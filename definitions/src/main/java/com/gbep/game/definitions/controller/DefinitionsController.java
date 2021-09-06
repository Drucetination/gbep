package com.gbep.game.definitions.controller;

import com.gbep.game.definitions.entity.Game;
import com.gbep.game.definitions.entity.Task;
import com.gbep.game.definitions.repository.DefinitionsRepo;
import com.gbep.game.definitions.service.DefinitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/definitions")
public class DefinitionsController {

    @Autowired
    private DefinitionsService definitionsService;


    @PostMapping("/")
    public Game saveGame(Game game) {
        return definitionsService.saveGame(game);
    };

    @GetMapping("/all")
    public List<Game> getAll() {
        return definitionsService.getAll();
    }

    @GetMapping("/{name}")
    public Optional<Game> findGameById(@PathVariable String name) {
        return definitionsService.findGameByName(name);
    }

    @DeleteMapping("/{name}")
    public Optional<Game> deleteGameByName(@PathVariable String name) {
        return definitionsService.deleteGameByName(name);
    }

    @PostMapping("/{name}/{task_id}")
    public Boolean checkAnswer(@PathVariable String name, @PathVariable String task_id, @RequestBody String answer) {
        return definitionsService.checkAnswer(name, task_id, answer);
    }




}
