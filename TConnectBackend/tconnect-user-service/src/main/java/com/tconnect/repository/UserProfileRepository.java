package com.tconnect.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfileEntity, Long> {
	public UserProfileEntity findByEmail(String email);
	
	@Query(value="{'type':'agent', 'orgId':?0}")
	public List<UserProfileEntity> findByOrgId(ObjectId orgId);

	@Query(value="{'email' : ?0}", fields="{'followedCompanyIds': 1}")
	public UserProfileEntity getFollowedCompaniesIds(String email);
}

