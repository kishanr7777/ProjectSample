package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long> {
}
