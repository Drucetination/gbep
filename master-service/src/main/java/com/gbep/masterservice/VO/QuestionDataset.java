package com.gbep.masterservice.VO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class QuestionDataset {
    private String question_id;
    private String name;
    private List<Question> questions;
}
