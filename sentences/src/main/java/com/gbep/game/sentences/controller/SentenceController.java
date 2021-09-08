package com.gbep.game.sentences.controller;


import com.gbep.game.sentences.entity.Pair;
import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.entity.UserAnswer;
import com.gbep.game.sentences.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping("/")
    public Optional<SentenceDataset> saveDataset(@RequestBody SentenceDataset dataset) {
        return sentenceService.saveDataset(dataset);
    }

    @GetMapping("/check_name/{name}")
    public Boolean checkIfExists(@PathVariable String name) {
        return sentenceService.checkIfExists(name);
    }

    @GetMapping("/all")
    public List<String> findAll() {
        return sentenceService.findAll();
    }

    @GetMapping("/{name}")
    public List<String> getQuestionList(@PathVariable("name") String name) {

        return sentenceService.getQuestionList(name);
    }

    @GetMapping("/{name}/{id}")
    public Pair getQuestion(@PathVariable String id, @PathVariable String name) {
        return sentenceService.getQuestion(id, name);
    }


    @DeleteMapping("/{name}")
    public Optional<SentenceDataset> deleteDatasetByName(@PathVariable("name") String name) {
        return sentenceService.deleteDatasetByName(name);
    }
}
