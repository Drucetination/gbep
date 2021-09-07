package com.gbep.game.definitions.repository;

import com.gbep.game.definitions.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefinitionsRepo extends MongoRepository<Game, Long> {
    Optional<Game> findGameByName(String name);
    Optional<Game> deleteGameByName(String name);
}
