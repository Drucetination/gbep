package com.gbep.game.sentences.controller;


import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.entity.UserAnswer;
import com.gbep.game.sentences.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sentences")
@RequiredArgsConstructor
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping("/")
    public Optional<SentenceDataset> saveDataset(@RequestBody SentenceDataset dataset) {
        return sentenceService.saveDataset(dataset);
    }

    @GetMapping("/all")
    public List<SentenceDataset> findAll() {
        return sentenceService.findAll();
    }

    @GetMapping("/{name}")
    public List<String> getQuestionList(@PathVariable("name") String name) {

        return sentenceService.getQuestionList(name);
    }

    @PostMapping("/{name}/{id}")
    public Boolean compareAnswers(@PathVariable("name") String name, @PathVariable("id") String question_id, @RequestBody UserAnswer answer) {
        return sentenceService.compareAnswers(name, question_id, answer);
    }

    @DeleteMapping("/{name}")
    public Optional<SentenceDataset> deleteDatasetByName(@PathVariable("name") String name) {
        return sentenceService.deleteDatasetByName(name);
    }
}
