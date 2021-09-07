package com.gbep.game.definitions.controller;

import com.gbep.game.definitions.entity.Answer;
import com.gbep.game.definitions.entity.Game;
import com.gbep.game.definitions.service.DefinitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/definitions")
public class DefinitionsController {

    @Autowired
    private DefinitionsService definitionsService;


    @PostMapping("/")
    public Game saveGame(@ RequestBody Game game) {
        return definitionsService.saveGame(game);
    }

    @GetMapping("/allgames")
    public List<Game> getAll() {
        return definitionsService.getAll();
    }

    @GetMapping("/all")
    public List<String> getAllNames() {
        return definitionsService.getAllNames();
    }

    @GetMapping("/{name}")
    public Game findGameById(@PathVariable String name) {
        return definitionsService.findGameByName(name);
    }

    @DeleteMapping("/{name}")
    public Optional<Game> deleteGameByName(@PathVariable String name) {
        return definitionsService.deleteGameByName(name);
    }

    @PostMapping("/{name}/{task_id}")
    public Boolean checkAnswer(@PathVariable String name, @PathVariable String task_id, @RequestBody Answer answer) {
        return definitionsService.checkAnswer(name, task_id, answer);
    }




}
