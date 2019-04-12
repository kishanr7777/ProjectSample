package com.tconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tconnect.dto.OpportunityDto;
import com.tconnect.dto.PostDto;
import com.tconnect.entity.OpportunityEntity;
import com.tconnect.entity.PostEntity;
import com.tconnect.service.PostService;

@RestController
@CrossOrigin(origins="*")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/opportunities/all")
	public OpportunityEntity[] getAllOpportunities() {
		return postService.getAllOpportunities();
	}
	@PostMapping(value="/post/add", headers="Accept=application/json")
	public ResponseEntity<Object> addOpportunity(@RequestBody OpportunityDto opportunityDto) {
		return ResponseEntity.ok(postService.addOpportunity(opportunityDto));
	}

	@PostMapping(value="/post/add", headers="Accept=application/json")
	public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
		return ResponseEntity.ok(postService.addPost(postDto));
	}
	
	@GetMapping(value="/post/all", headers="Accept=application/json")
	public PostEntity[] getAllPosts() {	
		return postService.getAllPosts();
	}
}
