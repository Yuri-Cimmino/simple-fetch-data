package it.eng.spring.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import it.eng.spring.demo.dto.ClienteDTOInput;
import it.eng.spring.demo.dto.ClienteDTOOutput;
import it.eng.spring.demo.dto.PolizzaDTO;
import it.eng.spring.demo.exception.Errore;
import it.eng.spring.demo.model.Cliente;
import it.eng.spring.demo.repository.ClienteRepository;
import it.eng.spring.demo.utils.Utils;

@Service
public class ClienteService {
	private ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
	private Long idCounter = 1L;
	private final ClienteRepository repository;
	private final static Logger log = LoggerFactory.getLogger(ClienteService.class);
	private final RestClient restClient = RestClient.create();
	@Value("${polizza.url}")
	private String urlCliente;

	public ClienteService(ClienteRepository cr) {
		this.repository = cr;
	}

	public ResponseEntity<Object> getClienti() {
		List<Cliente> listaClientiDb = repository.findAll();

		if (listaClientiDb.isEmpty()) {
			Errore errore = new Errore("CLI-003", "Lista clienti vuota");
			return ResponseEntity.status(404).body(errore);
		}

		return ResponseEntity.ok(listaClientiDb);
	}

	public ClienteDTOOutput getSpecificClient(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			return Utils.Client2ClientDTOOutput(cliente.get());
		}
		return Utils.Client2ClientDTOOutput(cliente.get());

	}

	public Cliente creaCliente(ClienteDTOInput clienteDTOInput) {
		Cliente cliente = Utils.ClientDTOInput2Client(clienteDTOInput);
		Cliente cl = repository.save(cliente);
		log.info(String.valueOf(cl.getId()));
		return cl;
	}

	public boolean eliminaCliente(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public List<PolizzaDTO> listaPolizze() {
		PolizzaDTO[] pl = restClient.get().uri(urlCliente).retrieve().body(PolizzaDTO[].class);
		List<PolizzaDTO> polizze = Arrays.asList(pl);
		return polizze;
	}

	public PolizzaDTO[] listaPolizze2() {
		PolizzaDTO[] pl = restClient.get().uri(urlCliente).retrieve().body(PolizzaDTO[].class);
		return pl;
	}
}
