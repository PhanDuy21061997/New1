package com.example.demo.dto;

import java.util.Collection;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;

public class CustomUser extends User {
	 private final int userID;
	 public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
             boolean credentialsNonExpired,
             boolean accountNonLocked,
             Collection<? extends GrantedAuthority> authorities, int userID) {
		 super();
		    
this.userID = userID;
}
}
