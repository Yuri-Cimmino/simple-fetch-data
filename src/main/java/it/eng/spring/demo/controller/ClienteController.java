package it.eng.spring.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.eng.spring.demo.dto.ClienteDTOInput;
import it.eng.spring.demo.dto.ClienteDTOOutput;
import it.eng.spring.demo.exception.BusinessException;
import it.eng.spring.demo.exception.Errore;
import it.eng.spring.demo.model.Cliente;
import it.eng.spring.demo.services.ClienteService;
import jakarta.validation.Valid;

@RestController
public class ClienteController {
	
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();

	private final ClienteService cService;

	private final static Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Value("${spring.application.name}")
	private String nomeApplicazione;

	@Value("${logging.level.root}")
	private String defaultlogginglevelroot;
	
	public ClienteController(ClienteService cService) {
		this.cService = cService;
	}
	
	@GetMapping("/default-log-level")
	public ResponseEntity<Object> getDefaultLogLevel() {
		log.error("ERROR");
		log.warn("WARN");
		log.info("INFO");
		log.debug("DEBUG");
		log.trace("TRACE");
		return ResponseEntity.ok("Log level di default: " + defaultlogginglevelroot);
	}

	@GetMapping("/nome-applicazione")
	public ResponseEntity<Object> getNomeApplicazione(){
		log.info("NOME: " + nomeApplicazione);
		return ResponseEntity.ok("Log level di default: " + nomeApplicazione);
	}
	
	@GetMapping("/clienti")
	public ResponseEntity<Object> getClienti() {
		return cService.getClienti();
	}
	

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Object> getSpecificClient(@PathVariable Long id, @RequestHeader Map<String, String> headers, @RequestHeader(value = "token", required = true) String token) {
		String language = headers.get("language");
		log.info(token);
		ClienteDTOOutput trovato = cService.getSpecificClient(id);
	  
		if(trovato != null) {
			log.info("Cliente trovato: " + trovato.getNome() +" "+ trovato.getCognome());
			return ResponseEntity.status(201).body(trovato);
		} else {
			log.error("Errore");
			Errore errore = new Errore("CLI-001", "Cliente non trovato !");
			return ResponseEntity.status(404).body(errore);
		}
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<Object> creaCliente(@RequestBody @Valid ClienteDTOInput cliente) throws BusinessException {
		Cliente nuovoCliente = cService.creaCliente(cliente);
		return ResponseEntity.status(201).body(nuovoCliente);
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
