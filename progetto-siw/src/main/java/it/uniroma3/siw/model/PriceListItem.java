package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PriceListItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int costo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	
	
	
}
