package com.example.demo.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.User_RoleRepository;
import com.example.demo.Server.JwtService;
import com.example.demo.Server.PersonnelServerI;

import com.example.demo.Server.Users_ServerI;
import com.example.demo.domain.ApiResponse;
import com.example.demo.dto.Add_personnel;
import com.example.demo.model.Personnel;
import com.example.demo.model.User;
import com.example.demo.model.User_Role;
import com.example.demo.util.CryptWithMD5;
import com.example.demo.util.EmailValidator;
import com.example.demo.util.Reporter;
import com.example.demo.util.ReturnObject;
import com.example.demo.util.TokenGenerator;

import freemarker.core.ReturnInstruction.Return;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/api/personel/")

public class APIController_User {

	@Autowired
	PersonnelServerI personnelServerI;

	@Autowired
	Users_ServerI userServerI;
	
	@Autowired
	PersonnelRepository personnelRepository;

	@RequestMapping(value = "/user/Passwork/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> Update(@Valid @RequestBody User user, @PathVariable(value = "id") int id) {

		return new ResponseEntity<ApiResponse>(userServerI.ChangePassword(user, id), HttpStatus.OK);

	}

	
	/*@RequestMapping(value = "/user/{id}/information", method = RequestMethod.GET)

	public ResponseEntity<ReturnObject> GetuserID(@PathVariable(value = "id") int id) {

		return new ResponseEntity<ReturnObject>(personnelServerI.GetuserID(id), HttpStatus.OK);
		
	}*/
	@RequestMapping(value = "/me", method = RequestMethod.GET)

	public ResponseEntity<ReturnObject> GetuserIDMEto(@AuthenticationPrincipal UserDetails userDetails) {

		Personnel  personnel=personnelRepository.findby_id(userDetails.getUsername());
		
		return new ResponseEntity<ReturnObject>(personnelServerI.GetuserID(personnel.getId_p()), HttpStatus.OK);
		
	}
	@RequestMapping(value = "/me", method = RequestMethod.PUT)

	public ResponseEntity<ApiResponse>UpdateME(@AuthenticationPrincipal UserDetails userDetails,@Valid @RequestBody Personnel personnel) {

		Personnel  personnel1=personnelRepository.findby_id(userDetails.getUsername());
		
		return new ResponseEntity<ApiResponse>(personnelServerI.UpdatePersonnel(personnel, personnel1.getId_p()), HttpStatus.OK);
		
	}

	/*@RequestMapping(value = "/user/{id}/information", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> Update(@Valid @RequestBody Personnel personnel,
			@PathVariable(value = "id") int id) {

		return new ResponseEntity<ApiResponse>(personnelServerI.UpdatePersonnel(personnel, id), HttpStatus.OK);
	}*/

}
