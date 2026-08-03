package it.eng.spring.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eng.spring.demo.model.Cliente;

@RestController
public class ClienteController {
	@GetMapping("/cliente")
	public Cliente getCliente() {
	 Cliente cliente1 = new Cliente("Lucia","Rossi",1l);
		//  System.out.println(cliente1.getNome());
		//  System.out.println(cliente1.getCognome());
	 return cliente1;
	}
	
	@GetMapping("/clienti")
	public ArrayList<Cliente> getClienti() {
		Cliente cliente1 = new Cliente("Lucia","Rossi",1l);
		Cliente cliente2 = new Cliente("Antonio","Falco",2l);
		ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
		
		listaClienti.add(cliente1);
		listaClienti.add(cliente2);
		
		return listaClienti;
	}

}
