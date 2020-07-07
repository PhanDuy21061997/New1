package com.example.demo.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString*/
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_u;
	private String username;
	private String passwork;
	private int id_personnel;

	// @OneToOne(targetEntity = Personnel.class,cascade =
	// CascadeType.ALL,orphanRemoval = true)
	// @JoinColumn(name = "id_u",referencedColumnName ="id_u")

	@OneToOne(targetEntity = Personnel.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_personnel", insertable = false, updatable = false)
	private Personnel personnel;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<User_Role> usersRoleses =new HashSet<User_Role>(0);

	public Set<User_Role> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(final Set<User_Role> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	public int getId_u() {
		return id_u;
	}

	public void setId_u(int id_u) {
		this.id_u = id_u;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswork() {
		return passwork;
	}

	public void setPasswork(String passwork) {
		this.passwork = passwork;
	}

	public int getId_personnel() {
		return id_personnel;
	}

	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}

	public User() {

	}

	public User(int id_u, String username, String passwork, int id_personnel, Personnel personnel) {
		super();
		this.id_u = id_u;
		this.username = username;
		this.passwork = passwork;
		this.id_personnel = id_personnel;
		this.personnel = personnel;

	}

	public User(int id_u, String username, String passwork, int id_personnel, Personnel personnel,
			Set<User_Role> usersRoleses) {
		super();
		this.id_u = id_u;
		this.username = username;
		this.passwork = passwork;
		this.id_personnel = id_personnel;
		this.personnel = personnel;
		this.usersRoleses = usersRoleses;
	}

	/*@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// for (Role role: this.usersRoleses) {
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		// }

		return authorities;
	}*/
	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 for (User_Role user_Role: this.usersRoleses) {
		authorities.add(new SimpleGrantedAuthority(user_Role.getRole().getName()));
		 }

		return authorities;
	}

}
