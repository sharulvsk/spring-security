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
/*
 * package com.example.SpringProject.Config; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.Customizer; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.NoOpPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration public class SecurityConfig{
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception { http.csrf(csrf->csrf.disable())
 * .authorizeHttpRequests(auth->auth.requestMatchers("/rest/**").hasAnyRole(
 * "ADMIN","USERSs") .anyRequest().authenticated())
 * .httpBasic(Customizer.withDefaults()); return http.build(); }
 * 
 * @Bean public UserDetailsService userDetailsService() { UserDetails
 * user1=User.withUsername("Priya").password("password").roles("ADMIN").build();
 * UserDetails
 * user2=User.withUsername("Arul").password("12345678").roles("USERS").build();
 * return new InMemoryUserDetailsManager(user1,user2); }
 * 
 * @Bean public static PasswordEncoder passwordEncoder() { return
 * NoOpPasswordEncoder.getInstance(); }
 * 
 * @Bean public BCryptPasswordEncoder encoder() { return new
 * BCryptPasswordEncoder(); } }
 */

package com.example.SpringProject.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.SpringProject.Service.CustomUserDetailsService;

@Configuration
public class SecurityConfig{
	@Autowired 
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService, BCryptPasswordEncoder encoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); //DaoAuthenticationProvider is configured through its constructor instead of calling setUserDetailsService().
		provider.setPasswordEncoder(encoder);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider provider) throws Exception{
		http.csrf(csrf->csrf.disable());
		http.authenticationProvider(provider);
		http.authorizeHttpRequests(auth->auth.anyRequest().permitAll());
		return http.build();
	}
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}


