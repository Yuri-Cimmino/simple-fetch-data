package it.eng.spring.demo.services;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.eng.spring.demo.dto.ClienteDTOInput;
import it.eng.spring.demo.dto.ClienteDTOOutput;
import it.eng.spring.demo.exception.Errore;
import it.eng.spring.demo.model.Cliente;
import it.eng.spring.demo.repository.ClienteRepository;
import it.eng.spring.demo.utils.Utils;

@Service
public class ClienteService {
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
	private Long idCounter = 1L;
	private final ClienteRepository repository;
	
	public ClienteService(ClienteRepository cr) {
		this.repository = cr;
	}


	public ResponseEntity<Object> getClienti() {
		
		if (listaClienti.isEmpty()) {
			Errore errore = new Errore("CLI-003", "Lista clienti vuota");
			return ResponseEntity.status(404).body(errore);
		}

		return ResponseEntity.ok(new ArrayList<>(listaClienti));
	}

	public ClienteDTOOutput getSpecificClient( Long id) {
		ClienteDTOOutput trovato = null;

		for(Cliente cliente : listaClienti) {
			if(cliente.getId() == id) {
				trovato = Utils.Client2ClientDTOOutput(cliente);
			}
		}
		return trovato;
	}
	
	public Cliente creaCliente(ClienteDTOInput clienteDTOInput) {
		Cliente cliente = Utils.ClientDTOInput2Client(clienteDTOInput);
		cliente.setId(idCounter++);
		listaClienti.add(cliente);
		return cliente;
	}
}
