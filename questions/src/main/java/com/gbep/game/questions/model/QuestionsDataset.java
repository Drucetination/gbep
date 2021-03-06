package com.gbep.game.questions.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class QuestionsDataset {
    @Id
    private String id;
    private String name;
    private List<Question> questions;

}
