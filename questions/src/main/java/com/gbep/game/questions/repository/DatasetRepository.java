package com.gbep.game.questions.repository;

import com.gbep.game.questions.model.QuestionsDataset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatasetRepository extends MongoRepository<QuestionsDataset, String> {
    Optional<QuestionsDataset> findQuestionsDatasetByName(String name);
}
