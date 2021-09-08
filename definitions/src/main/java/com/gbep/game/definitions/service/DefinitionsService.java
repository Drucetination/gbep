package com.gbep.game.definitions.service;

import com.gbep.game.definitions.entity.Answer;
import com.gbep.game.definitions.entity.Game;
import com.gbep.game.definitions.entity.Task;
import com.gbep.game.definitions.repository.DefinitionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefinitionsService {

    @Autowired
    private DefinitionsRepo repo;

    public Game saveGame(Game game) {
        return repo.save(game);
    }


    public List<String> findGameByName(String name) {
        return repo.findGameByName(name).get()
                .getTasks()
                .stream()
                .map(Task::getQuestion_id)
                .toList();

    }

    public Optional<Game> deleteGameByName(String name) {
        return repo.deleteGameByName(name);
    }

    public List<Game> getAll() {
        return repo.findAll();
    }

    public List<String> getAllNames() {
        return getAll().stream()
                .map(Game::getName)
                .toList();
    }

//    public Boolean checkAnswer(String name, String task_id, Answer answer) {
//            return findGameByName(name).getTasks().stream()
//                    .filter(task -> task.getQuestion_id().equals(task_id))
//                    .anyMatch(task -> task.getWord().equalsIgnoreCase(answer.getText()));
//    }

    public Task getQuestion(String id, String name) {
        Optional<Game> data = repo.findGameByName(name);
        if (data.isPresent()) {
            for (Task question : data.get().getTasks()) {
                if (Objects.equals(question.getQuestion_id(), id)) {
                    return question;
                }
            }
        }

        return new Task();
    }

    public Boolean checkIfExists(String name) {
        Optional<Game> d = repo.findGameByName(name);
        return d.isEmpty();
    }
}
