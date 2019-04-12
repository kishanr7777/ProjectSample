package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.OpportunityEntity;

@Repository
public interface OpportunityRepository extends MongoRepository<OpportunityEntity, Long>{
	
	@Query(value="{'type' : 'opportunity'}")
	public OpportunityEntity[] getAllOpportunities();
	
}
