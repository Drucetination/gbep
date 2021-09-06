package com.gbep.game.definitions;

import com.gbep.game.definitions.entity.Game;
import com.gbep.game.definitions.entity.Task;
import com.gbep.game.definitions.repository.DefinitionsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DefinitionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefinitionsApplication.class, args);
    }
}

