package com.gbep.game.sentences.service;

import com.gbep.game.sentences.entity.Pair;
import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class SentenceService {
    @Autowired
    private SentenceRepo repo;

    private static List<SentenceDataset> items = new ArrayList<>();

    public List<SentenceDataset> findAll() {
        return repo.findAll();
    }
}
