package com.example.demo.Server;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.ApiResponse;
import com.example.demo.model.User;


public interface Users_ServerI {

	public boolean findUserName(String Username);
	public ApiResponse ChangePassword(User user,int id);
	
}
