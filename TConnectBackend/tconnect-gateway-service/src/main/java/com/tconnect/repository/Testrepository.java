package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.TestEntity;

@Repository
public interface Testrepository extends MongoRepository<TestEntity, Long>{
	public TestEntity findByName(String name);
}
