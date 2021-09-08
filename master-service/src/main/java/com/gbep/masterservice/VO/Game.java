package com.gbep.masterservice.VO;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {


    private String id;
    private String name;
    private List<Task> tasks;

    public Game(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }


}
