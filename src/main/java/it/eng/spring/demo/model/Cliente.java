package it.eng.spring.demo.model;

public class Cliente {
	private String nome;
	private String cognome;
	private Long id;
	private String codiceFiscale;
	private String email;
	private String password;
	
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassowrd(String passowrd) {
		this.password = passowrd;
	}
	
	public Cliente(String nome, String cognome, Long id, String codiceFiscale, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.email = email;
		this.password = password;
	}
	
	public Cliente() {
	}
	
	
	
}
