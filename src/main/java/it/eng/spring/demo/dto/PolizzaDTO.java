package it.eng.spring.demo.dto;

import java.util.Date;

public class PolizzaDTO {
	private Long id;
	private String numeroPolizza;
	private String nomeProdotto;
	private Date dataEmissione;
	private Date dataScadenza;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroPolizza() {
		return numeroPolizza;
	}
	public void setNumeroPolizza(String numeroPolizza) {
		this.numeroPolizza = numeroPolizza;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}
	public Date getDataEmissione() {
		return dataEmissione;
	}
	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	public PolizzaDTO(Long id, String numeroPolizza, String nomeProdotto, Date dataEmissione, Date dataScadenza) {
		super();
		this.id = id;
		this.numeroPolizza = numeroPolizza;
		this.nomeProdotto = nomeProdotto;
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
	}
	
	public PolizzaDTO() {}
}
