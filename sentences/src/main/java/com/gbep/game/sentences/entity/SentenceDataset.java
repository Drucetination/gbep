package com.gbep.game.sentences.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class SentenceDataset {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private List<Pair> data;

    public SentenceDataset(String name, List<Pair> data) {
        this.name = name;
        this.data = data;
    }
}
