package com.example.SpringProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//Spring:2.x-with overriding
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.web.server.adapter.HttpWebHandlerAdapter;
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Override
//	public void configure(Http http) {
//		http.csrf().disable();
//		http.authorizeRequest().anyRequest().fullyAuthenticated().and().httpBasics();
//	}
//	@Override
//	public void configure(Auth auth) {
//		auth.inmemoryAuthentication().withUser().password().roles();
//	}
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
//}
//Spring:4.x-bean
@Configuration
public class SecurityConfig{
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth->auth.requestMatchers("/rest/**").hasAnyRole("ADMIN","USERSs")
				.anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1=User.withUsername("Priya").password("password").roles("ADMIN").build();
		UserDetails user2=User.withUsername("Arul").password("12345678").roles("USERS").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}