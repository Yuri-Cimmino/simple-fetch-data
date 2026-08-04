package it.eng.spring.demo.dto;

import jakarta.validation.constraints.NotNull;

public class ClienteDTOInput {
	@NotNull
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String email;
	private String password;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ClienteDTOInput(String nome, String cognome, String codiceFiscale, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.password = password;
	}
	
	public ClienteDTOInput() {

	}
	

}
