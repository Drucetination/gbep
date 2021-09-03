package com.gbep.game.sentences.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
public class Pair {
    private String word;
    private String sentence;
}
