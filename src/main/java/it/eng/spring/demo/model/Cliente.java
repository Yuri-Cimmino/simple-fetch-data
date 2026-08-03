package it.eng.spring.demo.model;

public class Cliente {
	private String nome;
	private String cognome;
	private Long id;
	
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
	
	public Cliente(String nome, String cognome, Long id) {
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
	}
	
	
}
