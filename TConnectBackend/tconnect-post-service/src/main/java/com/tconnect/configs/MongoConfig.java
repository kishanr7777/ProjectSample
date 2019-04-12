package com.tconnect.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.tatva.tconnectGeneralConfigs.TconnectConsts;

@Configuration
public class MongoConfig{

	@Bean
	public MongoClient mongo() {
		return new MongoClient(TconnectConsts.DB_HOST);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongo(), TconnectConsts.DB_NAME);
	}
}
