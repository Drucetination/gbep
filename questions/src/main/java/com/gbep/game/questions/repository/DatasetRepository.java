package com.gbep.game.questions.repository;

import com.gbep.game.questions.model.QuestionsDataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetRepository extends MongoRepository<QuestionsDataset, String> {
}
