package it.eng.spring.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/cliente/{id}")
	public Cliente getSpecificClient(@PathVariable Long id) {
	 Cliente cliente1 = new Cliente("Lucia","Rossi",id);
	 return cliente1;
	}
	
	@GetMapping("/clienti/{id}")
	public Cliente getSpecificClients(@PathVariable Long id) {
		Cliente cliente2 = new Cliente("Antonio","Falco",2l);
		Cliente cliente1 = new Cliente("Lucia","Rossi",1l);
		
		ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
		
		listaClienti.add(cliente2);
		listaClienti.add(cliente1);
		
		
		Cliente trovato = null;

		for(int i = 0; i < listaClienti.size(); i++ ) {
			Cliente c = listaClienti.get(i);
			
			if(c.getId() == id){
				trovato = c;
				break;
			}
			
		}
		return trovato;
		
	}
	

}
