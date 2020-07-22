package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manage_id")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Manage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_m;
	int id_personnel;

	@OneToOne(targetEntity = Personnel.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_personnel", insertable = false, updatable = false)
	private Personnel personnel;

	
	
/*	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}*/

	public int getId_m() {
		return id_m;
	}

	public void setId_m(int id_m) {
		this.id_m = id_m;
	}

	public int getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}

	public Manage(int id_m, int id_personnel) {
		super();
		this.id_m = id_m;
		this.id_personnel = id_personnel;
	}

	public Manage() {
		
	}

	/*public Manage(int id_m, int id_personnel, Personnel personnel) {
		super();
		this.id_m = id_m;
		this.id_personnel = id_personnel;
		this.personnel = personnel;
	}*/

}
