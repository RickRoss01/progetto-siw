package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int costoUnitario;
	private int quantita;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCostoUnitario() {
		return costoUnitario;
	}
	public void setCostoUnitario(int costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	
	
}
