package com.example.demo.Server;

import java.util.List;

import com.example.demo.domain.ApiResponse;
import com.example.demo.model.Personnel;
import com.example.demo.util.ReturnObject;

public interface PersonnelServerI {

	Personnel login(String username, String passwork);
	ReturnObject find_manage_p(int id_manage_p);
	ReturnObject loginAPI(String username, String passwork);
	ApiResponse addPersonnel(Personnel personnel);
	ApiResponse UpdatePersonnel(Personnel personnel,int id);
	ReturnObject GetuserID(int id);
	
	
	
}
