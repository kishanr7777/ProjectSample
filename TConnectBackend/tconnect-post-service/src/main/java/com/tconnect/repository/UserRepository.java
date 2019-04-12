package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, Long>{
	
	@Query(value="{'type' : 'company', 'name' : ?0}", fields="{'_id': 1}")
	public UserEntity getCompanyByName(String name);
	
	@Query(value="{'type' : 'agent', 'email' : ?0}", fields="{'_id': 1}")
	public UserEntity getAgentByEmail(String agentEmail);
	
	@Query(value="{'type' : 'university', 'name' : ?0}", fields="{'_id': 1}")
	public UserEntity getUniversityByName(String name);
}
