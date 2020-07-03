package com.example.demo.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "personnel")
public class Personnel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_p;

	private String name;
	private String address;
	private String email;
	private Date date_of_birth;
	private int id_manage_p;

	/*
	 * @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL,orphanRemoval =
	 * true)
	 * 
	 * @JoinColumn(name = "id_p",referencedColumnName ="id_p")
	 */

	@OneToOne(mappedBy = "personnel", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;

	public Personnel() {

	}

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId_manage_p() {
		return id_manage_p;
	}

	public void setId_manage_p(int id_manage_p) {
		this.id_manage_p = id_manage_p;
	}

	public Personnel(int id_p, String name, String address, String email, Date date_of_birth, int id_manage_p,
			User user) {
		super();
		this.id_p = id_p;
		this.name = name;
		this.address = address;
		this.email = email;
		this.date_of_birth = date_of_birth;
		this.id_manage_p = id_manage_p;
		this.user = user;
	}

}
