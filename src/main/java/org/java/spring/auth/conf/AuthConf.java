package org.java.spring.auth.conf;

import org.java.spring.auth.db.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class AuthConf {
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
		.cors().disable()
		.authorizeHttpRequests()
		.requestMatchers("/**").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/api/**").permitAll()
        .requestMatchers("/").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers(new RegexRequestMatcher("/pizze/[0-10]+", null)).hasAnyAuthority("ADMIN")
        .requestMatchers("/*/create/**").hasAuthority("ADMIN")
        .requestMatchers("/*/edit/**").hasAuthority("ADMIN")
        .and().formLogin()
        .and().logout();
		
		return http.build();
	}
	
	@Bean
    UserDetailsService userDetailsService() {
	    return new UserService();
	}
    
    @Bean
	public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
   
    	return authProvider;
    }


}
