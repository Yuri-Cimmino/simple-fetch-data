package it.eng.spring.demo.utils;

import it.eng.spring.demo.dto.ClienteDTOInput;
import it.eng.spring.demo.dto.ClienteDTOOutput;
import it.eng.spring.demo.model.Cliente;

public class Utils {
	public static final ClienteDTOOutput Client2ClientDTOOutput(Cliente cliente) {
		ClienteDTOOutput dto = new ClienteDTOOutput();
		dto.setNome(cliente.getNome());
		dto.setCognome(cliente.getCognome());
		dto.setCodiceFiscale(cliente.getCodiceFiscale());
		dto.setEmail(cliente.getEmail());
		return dto;
	}
	
	public static final Cliente ClientDTOInput2Client(ClienteDTOInput clienteDTOInput) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTOInput.getNome());
		cliente.setCognome(clienteDTOInput.getCognome());
		cliente.setCodiceFiscale(clienteDTOInput.getCodiceFiscale());
		cliente.setEmail(clienteDTOInput.getEmail());
		return cliente;
	}

}
