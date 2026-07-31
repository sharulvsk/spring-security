package com.example.SpringProject.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.SpringProject.Model.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SpringProject.Repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/admin/add")
	public String add(@RequestBody Users user) {
		String pass = user.getPassword();
		String passkey=encoder.encode(pass);
		user.setPassword(passkey);
		userRepository.save(user);
		return "User added successfully";
	}
}
