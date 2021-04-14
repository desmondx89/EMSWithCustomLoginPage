package com.example;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class EmployeeManagementSytemApplication {

	 @GetMapping("/user")
	    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
	        return Collections.singletonMap("name", principal.getAttribute("name"));
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSytemApplication.class, args);
	}

}
