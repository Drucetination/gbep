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

    private static List<Task> tasks = new ArrayList<>();

    public Game saveGame(Game game) {
        return repo.save(game);
    }


    public Optional<Game> findGameByName(String name) {
        return repo.findGameByName(name);

    }

    public Optional<Game> deleteGameByName(String name) {
        return repo.deleteGameByName(name);
    }

    public List<Game> getAll() {
        return repo.findAll();
    }

    public Boolean checkAnswer(String name, String task_id, Answer answer) {
        Optional<Game> game = repo.findGameByName(name);
        if (game.isPresent()) {
            for (Task task : game.get().getTasks()) {
                if (Objects.equals(task.getId(), task_id)) {
                    if (!Objects.equals(task.getWord().toLowerCase(), answer.getText().toLowerCase())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
