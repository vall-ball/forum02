package ru.vallball.forum02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.vallball.forum02.model.RegistrationForm;
import ru.vallball.forum02.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String registerForm() {
	return "registration";
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
	userService.save(form.toUser(passwordEncoder));
	return "redirect:/login";
	}

}
