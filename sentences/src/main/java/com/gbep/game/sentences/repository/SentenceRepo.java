package com.gbep.game.sentences.repository;

import com.gbep.game.sentences.entity.SentenceDataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SentenceRepo extends MongoRepository<SentenceDataset, String> {

    Optional<SentenceDataset> findSentenceDatasetByName(String name);
    Optional<SentenceDataset> findSentenceDatasetById(String id);
    Optional<SentenceDataset> deleteSentenceDatasetByName(String name);
}
