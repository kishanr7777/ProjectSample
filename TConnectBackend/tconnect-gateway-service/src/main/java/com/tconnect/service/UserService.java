package com.tconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tconnect.entity.TestEntity;
import com.tconnect.entity.UserEntity;
import com.tconnect.repository.Testrepository;
import com.tconnect.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(UserEntity user) {
		userRepository.save(user);
	}

	public UserEntity findByToken(String token) {
		return userRepository.findByToken(token);
	}

	
	@Autowired 
	Testrepository tr;
	
	public TestEntity testdb(String name, String oldname) {
//		List<UserEntity> user = userRepository.findAll();
		
		TestEntity ent = tr.findByName(name);
		
		int[] marks = {65,90,75,96,84};
		
		ent.setName(oldname);
		ent.setMarks(marks);
		
		
		return tr.save(ent);
	}
}
