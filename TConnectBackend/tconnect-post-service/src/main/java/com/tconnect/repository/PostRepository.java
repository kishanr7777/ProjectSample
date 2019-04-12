package com.tconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tconnect.entity.PostEntity;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, Long>{
	@Query(value="{'type':'post'}")
	public PostEntity[] getAllPosts();
}
