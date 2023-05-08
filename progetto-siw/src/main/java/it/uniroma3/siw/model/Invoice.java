package it.uniroma3.siw.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int costoTot;
	private Date dataFatt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCostoTot() {
		return costoTot;
	}
	public void setCostoTot(int costoTot) {
		this.costoTot = costoTot;
	}
	public Date getDataFatt() {
		return dataFatt;
	}
	public void setDataFatt(Date dataFatt) {
		this.dataFatt = dataFatt;
	}
	
	
}
