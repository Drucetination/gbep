package com.gbep.game.sentences.repository;

import com.gbep.game.sentences.entity.SentenceDataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SentenceRepo extends MongoRepository<SentenceDataset, String> {

}
