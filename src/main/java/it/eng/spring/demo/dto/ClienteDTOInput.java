package it.eng.spring.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTOInput {
	@NotNull(message = "Nome obbligatorio")
	@NotBlank(message = "Il campo nome non può essere vuoto")
	private String nome;
	@NotNull(message = "Cognome obbligatorio")
	private String cognome;
	@Size(min = 16, max=16)
	@NotBlank(message = "Il campo codice fiscale non può essere vuoto")
	@NotNull(message = "Il codice fiscale è un campo obbligatorio")
	@Pattern(regexp = "^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$", message = "Il formato del codice fiscale non è corretto, ricontrollare")
	private String codiceFiscale;
	@Email(message = "Il formato mail non è corretto")
	private String email;
	private String password;
//	@Min(18)
//	@Max(99)
//	private int eta;
//	@Positive
//	@PositiveOrZero
//	private BigDecimal importo;
//	@Past
//	@PastOrPresent
//	private Date dataNascita;
//	@Future
//	@FutureOrPresent
//	private Date dataScadenza;
	
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
