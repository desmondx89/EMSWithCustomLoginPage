package com.example.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.model.Users;

@Service
public interface UserService extends UserDetailsService {

	static Users save(Users registration) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Query("SELECT u FROM Users u WHERE u.username = :username")
	public Users getUserByUsername(@Param("username") String username);
	
}
