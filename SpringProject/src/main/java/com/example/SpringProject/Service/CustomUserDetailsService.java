package com.example.SpringProject.Service;


//Without creating CustomUserDetails
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.SpringProject.Model.Role;
import com.example.SpringProject.Model.Users;
import com.example.SpringProject.Repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByUsername(username).
				orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
		String[] roles=user.getRole()
				.stream()
				.map(Role::getRole)
				.toArray(String[]:: new);
	    return User.builder()
			    .username(user.getUsername())
			    .password(user.getPassword())
			    .roles(roles)
			    .build();	    
	}
}
