package it.eng.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.spring.demo.model.Cliente;
import it.eng.spring.demo.model.Errore;

@RestController
public class ClienteController {
	
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
	
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
	public ResponseEntity<Object> getSpecificClients(@PathVariable Long id) {
		Cliente trovato = null;

//			Cliente c = listaClienti.get(i);
//			
//			if(c.getId() == id){
//				trovato = c;
//				break;
//			}
//			
//		}
		
		for(Cliente cliente : listaClienti) {
			if(cliente.getId() == id) {
				trovato = cliente;
			}
		}
		
		if(trovato != null) {
			System.out.println("Cliente trovato: " + trovato.getNome() +" "+ trovato.getCognome());
			return ResponseEntity.status(201).body(trovato);
		} else {
			System.out.println("Errore");
			Errore errore = new Errore("CLI-001", "Cliente non trovato !");
			return ResponseEntity.status(404).body(errore);
//			return ResponseEntity.status(456).body(errore);
		}
		
		
//		try {
//			System.out.println("Cliente trovato: " + listaClienti.get(id.intValue()).getNome() +' '+ listaClienti.get(id.intValue()).getCognome());
//			return ResponseEntity.ok(listaClienti.get(id.intValue()));
//		} catch (IndexOutOfBoundsException err) {
//			System.out.println("Cliente non trovato");
//			return ResponseEntity.status(404).body("Cliente non trovato !");
//		} catch (NullPointerException err) {
//			System.out.println("Errore Null Pointer");
//			return ResponseEntity.status(404).body("(NullPointerException) Cliente non trovato !");
//		} catch (Throwable err) {
//			System.out.println("Errore Throwable");
//			return ResponseEntity.status(404).body("(Throwable) Cliente non trovato !");
//		}
	}
	
	
//	@GetMapping("/clienti/{id}")
//	public ResponseEntity<Object> getSpecificClients(@PathVariable Long id) {
//		Cliente cliente2 = new Cliente("Antonio","Falco",2l);
//		Cliente cliente1 = new Cliente("Lucia","Rossi",1l);
//		
//		ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
//		
//		listaClienti.add(cliente2);
//		listaClienti.add(cliente1);
//		
//		try {
//			System.out.println("Cliente trovato: " + listaClienti.get(id.intValue()).getNome() +' '+ listaClienti.get(id.intValue()).getCognome());
//			return ResponseEntity.ok(listaClienti.get(id.intValue()));
//		} catch (IndexOutOfBoundsException err) {
//			System.out.println("Cliente non trovato");
//			return ResponseEntity.status(404).body("Cliente non trovato !");
//		} catch (NullPointerException err) {
//			System.out.println("Errore Null Pointer");
//			return ResponseEntity.status(404).body("(NullPointerException) Cliente non trovato !");
//		} catch (Throwable err) {
//			System.out.println("Errore Throwable");
//			return ResponseEntity.status(404).body("(Throwable) Cliente non trovato !");
//		}	
//	}
	
	@PostMapping("/cliente")
	public ResponseEntity<Object> creaCliente(@RequestBody Cliente cliente) {
		boolean esiste = false;
		List<String> errori = new ArrayList<>();
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
			errori.add("Nome Obbligatorio");
//			Errore errBadReq = new Errore("NOM-02", "Nome obbligatorio !");
//			return ResponseEntity.badRequest().body(errBadReq);	
		}
		if(cliente.getCognome() == null || cliente.getCognome().trim().isEmpty()){
			errori.add("Cognome Obbligatorio");
//			Errore errBadReq = new Errore("COG-02", "Cognome obbligatorio !");
//			return ResponseEntity.badRequest().body(errBadReq);	
		}
		if(!errori.isEmpty()) {
			return ResponseEntity.badRequest().body(errori);	
		}
		
		for(Cliente tempCliente : listaClienti) {
			if(tempCliente.getId() == cliente.getId()) {
				esiste = true;
				break;
			}
		}
		
		if(!esiste ) {
			listaClienti.add(cliente);
			return ResponseEntity.status(201).body(cliente);
		}else {
			Errore errBadReq = new Errore("ADD-CLI-001", "Esiste già un clinete con id " + cliente.getId());
			return ResponseEntity.badRequest().body(errBadReq);	
		}
		
	}
	
}
