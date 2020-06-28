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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Server.JwtService;
import com.example.demo.Server.PersonnelServerI;
import com.example.demo.domain.ApiResponse;
import com.example.demo.dto.Add_personnel;
import com.example.demo.model.Personnel;
import com.example.demo.model.User;
import com.example.demo.util.EmailValidator;
import com.example.demo.util.Reporter;
import com.example.demo.util.ReturnObject;
import com.example.demo.util.TokenGenerator;

import freemarker.core.ReturnInstruction.Return;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/api")
public class APIController {

	
	 @Autowired
	  private JwtService jwtService;

	@Autowired
	PersonnelServerI personnelServerI;
	
	@Autowired
	PersonnelRepository personnelRepository ;
	
	@Autowired
	UserRepository userRepository ;
	
	
		
	@RequestMapping(value = "/user/login1", method = RequestMethod.POST)
	
	public ResponseEntity<Personnel> login1(@RequestBody User user){
		return new ResponseEntity<Personnel>(personnelServerI.login(user.getUsername(), user.getPasswork()), HttpStatus.CREATED);
	    }
	
	@RequestMapping(value = "/user/loginAPI", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> loginAPI(HttpServletRequest request,@RequestBody User user){
		String result = "TEST";
	    HttpStatus httpStatus = null;
	    try {
			if (personnelServerI.loginAPI(user.getUsername(), user.getPasswork()).getCode()==1) {
				result=jwtService.generateTokenLogin(user.getUsername());
				//result = "TESTif";
				httpStatus=HttpStatus.OK;
			}
			else {
		        result = "Wrong userId and password";
		        httpStatus = HttpStatus.BAD_REQUEST;
		        return new ResponseEntity<ApiResponse>(new ApiResponse(0,result), httpStatus);
		      }
		} catch (Exception e) {
			Reporter.getErrorLogger().error("Controoler.loginAPI", e);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(1,result), httpStatus);
	    }
	
	@RequestMapping(value="/user/all", method = RequestMethod.GET)
	public ResponseEntity<List<Personnel>> listAll_p(){
		List<Personnel> listContact=(List<Personnel>) personnelRepository.findAll();
		if(listContact.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Personnel>>(listContact, HttpStatus.OK);
	}
	/*Them user */
	@RequestMapping(value = "/user/",method = RequestMethod.POST)
	public ResponseEntity<ReturnObject>ADD(@Valid @RequestBody Personnel personnel){
		
		if(personnel.getAddress()==null || personnel.getAddress()==null)
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR ADD",null),HttpStatus.OK);
		}
		else {
			personnelRepository.save(personnel);		
		}
		
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"success",personnel),HttpStatus.OK);
	}
	/*update*/
	@RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
	public ResponseEntity<ReturnObject>Update(@Valid @RequestBody Personnel personnel,@PathVariable (value ="id") int id){
		
		Personnel personnel2=personnelRepository.getOne(id);
		User user;
		if(personnel.getAddress()==null || personnel.getAddress()==null||personnel2==null)
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR",null),HttpStatus.OK);
		}
		if(new EmailValidator().validate(personnel.getEmail())==false )
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR EMAIL",null),HttpStatus.OK);
		}
		else {
			personnel2.setDate_of_birth(personnel.getDate_of_birth());
			personnel2.setEmail(personnel.getEmail());
			personnel2.setAddress(personnel.getAddress());
			personnel2.setName(personnel.getName());
		//	personnel2.setUser(personnel.setUser(personnel.getUser().getPasswork());
			personnelRepository.save(personnel2);		
		}
		
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"success",personnel),HttpStatus.OK);
	}
	@RequestMapping(value = "/user/Passwork/{id}",method = RequestMethod.PUT)
	public ResponseEntity<ReturnObject>Update(@Valid @RequestBody User personnel,@PathVariable (value ="id") int id){
		
		User user=userRepository.getOne(id);
		
		if(personnel.getPasswork()==null ||user==null)
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR",null),HttpStatus.OK);
		}
		else {
			user.setPasswork(personnel.getPasswork());
		//	personnel2.setUser(personnel.setUser(personnel.getUser().getPasswork());
			userRepository.save(user);		
		}
		
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"success",personnel),HttpStatus.OK);
	}
	@RequestMapping(value = "/user/",method = RequestMethod.PUT)
	public ResponseEntity<ReturnObject>Update(@Valid @RequestBody Personnel personnel){
		
	//	Personnel personnel2=personnelRepository.getOne(id);
	//	User user;
		if(personnel.getAddress()==null || personnel.getAddress()==null||personnel==null)
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR",null),HttpStatus.OK);
		}
		else {			
			personnelRepository.save(personnel);		
		}
		
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"success",personnel),HttpStatus.OK);
	}
	@RequestMapping(value = "/user/user",method = RequestMethod.POST)
	public ResponseEntity<ReturnObject>ADDuser(@Valid @RequestBody User user){
		
		/*if (add_personnel.getPersonnel().getAddress()==null ||add_personnel.getPersonnel().getAddress()==null) {
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR",null),HttpStatus.OK);
		}
		else {
			personnelRepository.save(add_personnel.getPersonnel());
		//	user.setId_personnel(personnel.getId_p());
			userRepository.save(add_personnel.getUser());
		}
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"Thanh Cong",personnel),HttpStatus.OK);*/
		/*if(user.getUsername()==null || user.getPasswork()==null || personnelRepository.getOne(user.getId_personnel())!=null )
		{
			return  new ResponseEntity<ReturnObject>( new ReturnObject(0,"ERROR",null),HttpStatus.OK);
		}
		else {
		//	personnelRepository.save(personnel);
		//	user.setId_personnel(personnel.getId_p());
			userRepository.save(user);
		}*/
		userRepository.save(user);
		return new ResponseEntity<ReturnObject>( new ReturnObject(1,"success",user),HttpStatus.OK);
	}
	/*xoa user*/
	@RequestMapping(value = "/user/{id}",method =RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> delete(@PathVariable (value ="id") int id ){
		User user=userRepository.getOne(id);
		if( user == null)
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(0,"ERROR in ID"),HttpStatus.BAD_REQUEST);
		}
		
		userRepository.delete(user);
		return new ResponseEntity<ApiResponse>(new ApiResponse(1,"success delete"),HttpStatus.OK);
	}
	/*xoa nhan vien*/
	@RequestMapping(value = "/NV/{id}",method =RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deleteNV(@PathVariable (value ="id") int id ){
		Personnel personnel= personnelRepository.getOne(id);
		if( personnel == null)
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(1,"ERROR in ID"),HttpStatus.BAD_REQUEST);
		}
		
		personnelRepository.delete(personnel);
		return new ResponseEntity<ApiResponse>(new ApiResponse(0,"success delete personnel"),HttpStatus.OK);
	}
	@RequestMapping(value = "/user/{id}",method =RequestMethod.GET)
	public ResponseEntity<ReturnObject> GetuserID(@PathVariable (value ="id") int id ){
		User user=userRepository.getOne(id);
		if( user == null)
		{
			return new ResponseEntity<ReturnObject>(new ReturnObject(0, "ERROR in  id", null),HttpStatus.OK);
		}
		
		//userRepository.delete(user);
		return new ResponseEntity<ReturnObject>(new ReturnObject(0,"success", user),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/personnel/{id}",method =RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deletepersonnel(@PathVariable (value ="id") int id ){
		Personnel personnel=personnelRepository.getOne(id);
		if( personnel== null)
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(0,"ERROR in ID"),HttpStatus.BAD_REQUEST);
		}
		
		personnelRepository.deleteById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(1,"success delete"),HttpStatus.OK);
	}
}
