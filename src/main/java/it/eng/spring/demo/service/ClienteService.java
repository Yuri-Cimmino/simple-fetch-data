package it.eng.spring.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.eng.spring.demo.exception.BusinessException;
import it.eng.spring.demo.exception.Errore;
import it.eng.spring.demo.model.Cliente;

@Service
public class ClienteService {
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();

	public Cliente getSpecificClient( Long id) {
		Cliente trovato = null;

		for(Cliente cliente : listaClienti) {
			if(cliente.getId() == id) {
				trovato = cliente;
			}
		}
		return trovato;
	}
	
	@PostMapping("/cliente")
	public Cliente creaCliente(@RequestBody Cliente cliente) throws BusinessException {
		List<Errore> errori = new ArrayList<>();
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
			errori.add(new Errore("NOM-01", "Nome obbligatorio"));
		}
		if(cliente.getCognome() == null || cliente.getCognome().trim().isEmpty()){
			errori.add(new Errore("COG-01", "Cognome obbligatorio"));
		}
		if(!errori.isEmpty()) {
		 throw new BusinessException(errori);	
		}
		
		for(Cliente tempCliente : listaClienti) {
			if(tempCliente.getId() == cliente.getId()) {
				errori.add(new Errore("ADD-CLI-001", "Esiste già un cliente con id " + cliente.getId()));
				 throw new BusinessException(errori);	
			}
		}
		listaClienti.add(cliente);
		return cliente;
	}
}
