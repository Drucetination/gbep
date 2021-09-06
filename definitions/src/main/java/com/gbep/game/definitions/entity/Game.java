package com.gbep.game.definitions.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
public class Game {

    @Id
    @GeneratedValue
    private String id;
    @Indexed(unique = true)
    private String name;
    private List<Task> tasks;

    public Game(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }


}
