package com.gbep.masterservice.repository;

import com.gbep.masterservice.entity.UserConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserConfigRepo extends MongoRepository<UserConfig, String> {
    Optional<UserConfig> findUserConfigByUserid(String user_id);
}
