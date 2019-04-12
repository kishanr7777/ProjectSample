package com.tconnect.service;

import java.util.Date;
import java.util.LinkedHashSet;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.tconnect.configs.PostServiceConsts;
import com.tconnect.dto.OpportunityDto;
import com.tconnect.entity.OpportunityEntity;
import com.tconnect.entity.ResponseStatus;

import com.tconnect.dto.PostDto;
import com.tconnect.entity.PostEntity;
import com.tconnect.entity.UserEntity;
import com.tconnect.repository.OpportunityRepository;
import com.tconnect.repository.PostRepository;
import com.tconnect.repository.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private OpportunityRepository opportunityRepository;
	@Autowired
	private UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@Transactional
	public OpportunityEntity[] getAllOpportunities() {
		return opportunityRepository.getAllOpportunities();
	}
	
	@Transactional
	public ResponseStatus addOpportunity(OpportunityDto ppo) {
		ResponseStatus rs = ppo.Validate();
		
		if(rs != null)
			return rs;
		
		OpportunityEntity oppo = new OpportunityEntity();
		oppo.setHeadline(ppo.getHeadline());
		oppo.setJobType(ppo.getJobType());
		oppo.setLocation(ppo.getLocation());
		oppo.setJobFunction(ppo.getJobFunction());
		oppo.setDescription(ppo.getDescription());
		oppo.setUrl(ppo.getUrl());
		oppo.setDeadline(ppo.getDeadline());
		oppo.setCreationDate(new Date(System.currentTimeMillis()));
		oppo.setPublic(ppo.isPublic());
		
		UserEntity company = userRepository.getCompanyByName(ppo.getCompanyName());
		if(company == null) {
			return new ResponseStatus(false, PostServiceConsts.MESSAGE_ERROR_INVALID_COMPANY_NAME);
		}
		oppo.setCompany(company.get_id());
		
		UserEntity agent = userRepository.getAgentByEmail(ppo.getAgentEmail());
		if(agent == null) {
			return new ResponseStatus(false, PostServiceConsts.MESSAGE_ERROR_INVALID_AGENT_EMAIL);
		}
		oppo.setAgent(agent.get_id());
		
		LinkedHashSet<String> universities = ppo.getUniversities();
		LinkedHashSet<ObjectId> universitiesIds = new LinkedHashSet<>();
		for(String uni : universities) {
			UserEntity university = userRepository.getUniversityByName(uni);
			if(university != null)
				universitiesIds.add(university.get_id());
		}
		oppo.setUniversities(universitiesIds);
		
		oppo = opportunityRepository.save(oppo);
		
		if(oppo == null || oppo.get_id() == null) {
			return new ResponseStatus(false, PostServiceConsts.MESSAGE_ERROR_POST_CAN_NOT_BE_SAVED);
		}
		
		logger.info("Post saved successfully by + " + ppo.getAgentEmail());
		
		return new ResponseStatus(true, PostServiceConsts.MESSAGE_ERROR_POST_CAN_NOT_BE_SAVED);
	}
	

	@Transactional
	public String addPost(PostDto postDto) {
		PostEntity postEntity = new PostEntity();
		postEntity.setContent(postDto.getContent());
		LinkedHashSet<String> universities = postDto.getUniversities();
		LinkedHashSet<ObjectId> universitiesIds = new LinkedHashSet<>();
		for (String uni : universities) {
			UserEntity university = userRepository.getUniversityByName(uni);
			if (university != null)
				universitiesIds.add(university.get_id());
		}
		postEntity.setUniversities(universitiesIds);
		postEntity.setPublic(postDto.isPublic());
		postEntity.setCreationDate(new Date(System.currentTimeMillis()));
		
		UserEntity agentProfile = userRepository.getAgentByEmail(postDto.getAgentEmail());
		if (agentProfile == null)
			return null;
		else
			postEntity.setAgentId(agentProfile.get_id());
		
		UserEntity companyProfile = userRepository.getCompanyByName(postDto.getCompanyName());
		if (companyProfile == null)
			return null;
		else
			postEntity.setCompanyId(companyProfile.get_id());
		
		postEntity.setType(PostServiceConsts.POST);
		postRepository.save(postEntity);
		logger.info("Post added successfully by " + postDto.getAgentEmail());
		return PostServiceConsts.MESSAGE_POST_ADDED_SUCCESSFULLY;
	}
	
	@Transactional
	public PostEntity[] getAllPosts() {
		return postRepository.getAllPosts();
	}

}
