package it.eng.spring.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodbyeController {
	@GetMapping("/goodbye")
	public String Goodbye() {
		return "A mai più";
	}

}
