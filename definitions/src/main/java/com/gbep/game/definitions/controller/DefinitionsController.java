package com.gbep.game.definitions.controller;

import com.gbep.game.definitions.entity.Game;
import com.gbep.game.definitions.entity.Task;
import com.gbep.game.definitions.service.DefinitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/")
public class DefinitionsController {

    @Autowired
    private DefinitionsService definitionsService;


    @PostMapping("/")
    public Game saveGame(@RequestBody Game game) {
        int start = 0;
        for (Task task : game.getTasks()) {
            task.setQuestion_id(String.valueOf(start));
            start++;
        }
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
    public List<String> findGameById(@PathVariable String name) {
        return definitionsService.findGameByName(name);
    }

    @DeleteMapping("/{name}")
    public Optional<Game> deleteGameByName(@PathVariable String name) {
        return definitionsService.deleteGameByName(name);
    }

    @GetMapping("/{name}/{id}")
    public Task getQuestion(@PathVariable String id, @PathVariable String name) {
        return definitionsService.getQuestion(id, name);
    }

    @GetMapping("/check_name/{name}")
    public Boolean checkName(@PathVariable String name) {
        return definitionsService.checkIfExists(name);
    }

//    @PostMapping("/{name}/{task_id}")
//    public Boolean checkAnswer(@PathVariable String name, @PathVariable String task_id, @RequestBody Answer answer) {
//        return definitionsService.checkAnswer(name, task_id, answer);
//    }


}
