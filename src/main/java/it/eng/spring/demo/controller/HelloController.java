package it.eng.spring.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/ciao")
	public String ciao() {
		return "Ciao dal Controller";
	}
	
	@GetMapping("/message")
	public String getMessage() {
		return "Questo è un messaggio dato dal HelloController";
	}

}
