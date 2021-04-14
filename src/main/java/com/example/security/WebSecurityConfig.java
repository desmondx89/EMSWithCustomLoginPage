package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	 ClientRegistrationRepository clientRegistrationRepository;
		
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
//	 private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
//	        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//	        successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
//	        return successHandler;
//	        		
//	 }
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
			.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.permitAll()
			.and()
			
			.logout()
			.invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID","XSRF-TOKEN","G_AUTHUSER_H")
            .logoutUrl("/logout")
//            .logoutSuccessHandler(oidcLogoutSuccessHandler())
            .logoutSuccessUrl("/login")
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            
            
			.permitAll()
			.and()
			
			.oauth2Login()
			.loginPage("/login")
			.permitAll()
			
			

			;
			
			
//			.and()
//			.csrf()
//			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//			;
		
//		End point level restriction
//			.antMatchers("/").hasAnyAuthority("user", "admin")
//			.antMatchers("/employees/new").hasAnyAuthority("admin")
////			.antMatchers("/edit/**").hasAnyAuthority("admin", "user")
////			.antMatchers("/delete/**").hasAuthority("admin")
//			.anyRequest().authenticated()
//			.and()
//			.formLogin().permitAll().defaultSuccessUrl("/department")
//			.and()
//			.logout().permitAll()
//			.and()
//			.exceptionHandling().accessDeniedPage("/403")
//			;
	}
	
}
