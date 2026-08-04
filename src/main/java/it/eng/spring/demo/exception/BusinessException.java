package it.eng.spring.demo.exception;

import java.util.List;

public class BusinessException extends RuntimeException {
	private final List<Errore> listaErrori;
	
	public List<Errore> getListaErrori() {
		return listaErrori;
	}

	public BusinessException(List<Errore> listaErrori) {
		this.listaErrori = listaErrori;
	}

}
