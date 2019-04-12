package com.tconnect.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tconnect.entity.Organisation;

public interface OrganisationRepository extends MongoRepository<Organisation, Long> {
	@Query(value="{'type':'company', 'name':{$regex:?0,$options:'i'}}")
	public Organisation findByCompanyName(String name);
	
	@Query(value="{'type':'company'}")
	public Organisation[] getAllCompanies();
	
	@Query(value="{'type':'university'}")
	public Organisation[] getAllUniversities();

	@Query(value="{'type':'university', 'name':?0}")
	public Organisation findByUniversity(String university);
	
	@Query(value="{'type':'university'}", fields= "{'name': 1}")
    public Organisation[] getAllUniversitiesNames();

	@Query(value="{'type':'company'}", fields= "{'_id' : 1, 'name' : 1, 'profileImageURL' : 1}")
	public Organisation[] getallCompaniesNames();
	
	public Organisation findBy_id(ObjectId id);

	@Query(value="{'type':'company', 'name':{$regex:?0,$options:'i'}}", fields="{'name' : 1, 'profileImageURL' : 1}")
	public Organisation getCompanyNameAndProfileImage(String name);

	@Query(value="{'type':'company'}", fields= "{'_id' : 1, 'name' : 1, 'profileImageURL' : 1, "
			+ "'size' : 1, 'totalOpportunities' : 1}")
	public Organisation[] getCompaniesNamesWithOpportunitiesAndSize();
}
