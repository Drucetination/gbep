package com.gbep.userservice.repository;

import com.gbep.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("select p.level from User p where p.id = :userId" )
  Long  getLevelById(Integer userId);

  @Query("select p.numberOfGames from User p where p.id = :userId" )
  Long getNumbersOfGameById(Integer userId);

  @Modifying
  @Transactional
  @Query("update User u set u.level = 1+u.numberOfGames/10, u.numberOfGames = u.numberOfGames+1 where u.id = :userId")
  void updateUserStatus(Integer userId);

}

