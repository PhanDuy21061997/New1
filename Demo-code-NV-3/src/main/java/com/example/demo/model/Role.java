package com.example.demo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id_r;
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_r() {
		return id_r;
	}

	public void setId_r(int id_r) {
		this.id_r = id_r;
	}

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<User_Role> usersRoleses ;

	public Set<User_Role> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(Set<User_Role> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	public Role(String name, Set<User_Role> usersRoleses) {
		super();
		this.name = name;
		this.usersRoleses = usersRoleses;
	}
	

	public Role() {

	}

	public Role( String name) {
		this.name = name;
	}
}
