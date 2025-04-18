package com.lavanya.spring_boot_web_application.FirstWebApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration   {
    //Inmemory
    @Bean
    public InMemoryUserDetailsManager createInMemoryUserDetailsManager() {

        UserDetails userDetails1 = getUserDetails("Lavanya", "Laav");
        UserDetails userDetails2 = getUserDetails("Seetha", "Laav");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails getUserDetails(String username, String password) {
        Function<String, String> passwordEncoder =
                input -> passwordEncoder().encode(input);

        UserDetails userDetails =User.builder()
                        .passwordEncoder(passwordEncoder)
                        .username(username).password(password).roles("USER","ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
  //All URLs are protected
  	//A login form is shown for unauthorized requests
  	//CSRF disable
  	//Frames
  	
  	@Bean
  	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  		
  		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
				
		
		http.formLogin(withDefaults());
		
		//http.csrf().disable(); //POST or PUT
		
		//http.headers().frameOptions().disable();
		
		return http.build();
  	}
  	


    
}
