package com.example.SpringProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/rest/auth")
public class AuthContoller {
	@GetMapping("/getMsg")
	public String greet() {
		return "Spring security";
	}
}
