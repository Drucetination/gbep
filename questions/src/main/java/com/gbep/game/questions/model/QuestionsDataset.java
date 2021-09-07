package com.gbep.game.questions.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsDataset {

    @Id
    private String id;
    @Column(unique = true)
    private String name;

    private List<Question> questions;

}
