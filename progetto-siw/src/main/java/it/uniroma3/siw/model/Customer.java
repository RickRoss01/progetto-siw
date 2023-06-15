package it.uniroma3.siw.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String ragioneSociale;
	private String nazione;
	@NotBlank
	private String pIva;
	private String indirizzo;
	private Integer fax;
	private String cap;
	private Integer telefono;
	private String email;
	private Integer numeroCivico;

	@CreationTimestamp
    private Instant createdOn;

	@OneToMany(mappedBy = "customer")
	private List<Contact> contacts;

	@OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
	private List<Order> orders;
	
	
	public Customer() {
		this.orders = new ArrayList<Order>();
		this.contacts = new ArrayList<Contact>();
	}
	
	public Integer getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(Integer numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public Integer getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(Integer fax) {
		this.fax = fax;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	
	public String getNazione() {
		return nazione;
	}
	
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public Integer getTelefono() {
		return telefono;
	}
	
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
		result = prime * result + ((cap == null) ? 0 : cap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		return true;
	}

	
	
	
}
