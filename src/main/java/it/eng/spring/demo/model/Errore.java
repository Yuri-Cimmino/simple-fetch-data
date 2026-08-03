package it.eng.spring.demo.model;

public class Errore {
	private String codice;
	private String messaggio;
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public Errore(String codice, String messaggio) {
		this.codice = codice;
		this.messaggio = messaggio;
	}
}
