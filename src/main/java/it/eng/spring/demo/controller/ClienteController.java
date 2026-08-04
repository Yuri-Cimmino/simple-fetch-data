package it.eng.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.eng.spring.demo.exception.Errore;
import it.eng.spring.demo.model.Cliente;

@RestController
public class ClienteController {
	
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
	
	// @GetMapping("/cliente")
	// public Cliente getCliente() {
	//  Cliente cliente1 = new Cliente("Lucia","Rossi",1l);
	// 	//  System.out.println(cliente1.getNome());
	// 	//  System.out.println(cliente1.getCognome());
	//  return cliente1;
	// }
	
	// @GetMapping("/cliente/{id}")
	// public Cliente getSpecificClient(@PathVariable Long id) {
	//  Cliente cliente1 = new Cliente("Lucia","Rossi",id);
	//  return cliente1;
	// }
	
	@GetMapping("/clienti")
	public ResponseEntity<Object> getClienti() {

		if(listaClienti.isEmpty()) {
			Errore errore = new Errore("CLI-003","Lista clienti vuota");
			return ResponseEntity.status(404).body(errore);
		}

		return ResponseEntity.ok(listaClienti);
	}
	

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Object> getSpecificClients(@PathVariable Long id) {
		Cliente trovato = null;

//		Cliente c = listaClienti.get(i);
//
//		if(c.getId() == id){
//			trovato = c;
//			break;
//		}
//	}
		
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
		}
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
		// boolean esiste = false;
		List<Errore> errori = new ArrayList<>();
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
			errori.add(new Errore("NOM-01", "Nome obbligatorio"));
		}
		if(cliente.getCognome() == null || cliente.getCognome().trim().isEmpty()){
			errori.add(new Errore("COG-01", "Cognome obbligatorio"));
		}
		if(!errori.isEmpty()) {
			return ResponseEntity.badRequest().body(errori);	
		}
		
		for(Cliente tempCliente : listaClienti) {
			if(tempCliente.getId() == cliente.getId()) {
				// esiste = true;
				// break;
				errori.add(new Errore("ADD-CLI-001", "Esiste già un cliente con id " + cliente.getId()));
				return ResponseEntity.badRequest().body(errori);
			}
		}
		
		// if(!esiste ) {
			listaClienti.add(cliente);
			return ResponseEntity.status(201).body(cliente);
		// }else {
		// 	Errore errBadReq = new Errore("ADD-CLI-001", "Esiste già un clinete con id " + cliente.getId());
		// 	return ResponseEntity.badRequest().body(errBadReq);	
		// }
		
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Object> modificaCliente(@PathVariable Long id, @RequestBody Cliente clienteToModify){
		Cliente trovato = null;
		for(Cliente tempCliente : listaClienti) {
			if(tempCliente.getId() == id ) {
				trovato = tempCliente;
				break;
			}
		}

		try{
			if(trovato != null) {
				if (clienteToModify.getNome() != null) { 
						trovato.setNome(clienteToModify.getNome()); 
					}
				
				if (clienteToModify.getCognome() != null) {
						trovato.setCognome(clienteToModify.getCognome()); 
					}
			}else {
				return ResponseEntity.status(400).body("Risorsa non trovata");	
			}
			return ResponseEntity.ok(trovato);	
		}catch(Throwable err) {
			return  ResponseEntity.status(400).body("Problema nella richiesta");	
		}
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Object> eliminaCliente(@PathVariable Long id){
		for(Cliente tempCliente : listaClienti) {
			if(tempCliente.getId() == id ) {
				listaClienti.remove(tempCliente);
				return ResponseEntity.ok(new Errore("CLI-0010","Cancellazione cliente effettuato"));
			}
		}
		Errore errore = new Errore("CLI-009","Cliente non trovato!");
		return ResponseEntity.status(404).body(errore);	
		
	}
	
}
