package com.gbep.masterservice.VO;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String question_id = "";
    private String word;
    private String definition;

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
