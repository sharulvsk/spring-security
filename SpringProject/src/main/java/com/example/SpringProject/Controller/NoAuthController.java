package com.example.SpringProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noauth/rest")
public class NoAuthController {
	@GetMapping("/getNoAuthMsg")
	public String msg() {
		return "No Auth";
	}
}
