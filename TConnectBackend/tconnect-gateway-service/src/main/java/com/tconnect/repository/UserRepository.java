package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	
	@Query(value="{'resetPassword.token' : ?0}")
	UserEntity findByToken(String token);
}
