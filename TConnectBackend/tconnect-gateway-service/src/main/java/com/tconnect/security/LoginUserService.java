package com.tconnect.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tconnect.entity.UserEntity;
import com.tconnect.repository.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
		
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email); 
		
		if (userEntity == null)
			throw new UsernameNotFoundException("No userdetail found for " + email);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for(String role : userEntity.getRoles())
			authorities.add(new SimpleGrantedAuthority(role));
		
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
				userEntity.isActive(), true, true, true, authorities);
	}
}
