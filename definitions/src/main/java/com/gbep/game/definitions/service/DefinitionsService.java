package com.gbep.game.definitions.service;

import com.gbep.game.definitions.entity.Answer;
import com.gbep.game.definitions.entity.Game;
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


    public Game findGameByName(String name) {
        return repo.findGameByName(name).orElse(null);

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

    public Boolean checkAnswer(String name, String task_id, Answer answer) {
            return findGameByName(name).getTasks().stream()
                    .filter(task -> task.getId().equals(task_id))
                    .anyMatch(task -> task.getWord().equalsIgnoreCase(answer.getText()));
    }
}
