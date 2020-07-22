package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Repository.ManageRepository;
import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.User_RoleRepository;
import com.example.demo.Server.JwtService;
import com.example.demo.Server.PersonnelServerI;
import com.example.demo.Server.Users_ServerI;
import com.example.demo.domain.ApiResponse;
import com.example.demo.dto.Page;
import com.example.demo.model.Manage;
import com.example.demo.model.Personnel;
import com.example.demo.model.User;
import com.example.demo.model.User_Role;
import com.example.demo.util.Reporter;
import com.example.demo.util.ReturnObject;

@Controller
@RequestMapping("/api/admin")
public class APIController_Admin {

	@Autowired
	private JwtService jwtService;

	@Autowired
	PersonnelServerI personnelServerI;

	@Autowired
	PersonnelRepository personnelRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	User_RoleRepository user_RoleRepository;

	@Autowired
	Users_ServerI userServerI;

	@Autowired
	ManageRepository manageRepository;

	@RequestMapping(value = "/manage/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> PUT_Manage(@Valid @RequestBody Manage manage,
			@PathVariable(value = "id") int id) {
		Manage manage1 = manageRepository.getOne(id);
		if (manage1 != null) {
			manage1.setId_personnel(manage.getId_personnel());
			manageRepository.save(manage1);
			return new ResponseEntity<ApiResponse>(new ApiResponse(0, "SUCCESS"), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(0, "ERROR in ID"), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/manage/", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> POST_Manage(@Valid @RequestBody Manage manage) {
		manageRepository.save(manage);
		return new ResponseEntity<ApiResponse>(new ApiResponse(0, "ERROR in ID"), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/user/seach", method = RequestMethod.GET)
	public ResponseEntity<ReturnObject> SEACH_p(@RequestBody Personnel personnel) {
		List<Personnel> list = new ArrayList<Personnel>();
		list = personnelRepository.Seach_p(personnel.getName());
		if (list == null) {
			return new ResponseEntity<ReturnObject>(new ReturnObject(1, "does not exist", null), HttpStatus.OK);

		}
		return new ResponseEntity<ReturnObject>(new ReturnObject(0, "SUCCESS", list), HttpStatus.OK);
	}

	@RequestMapping(value = "/page/{page}/number/{number}", method = RequestMethod.GET)
	public ResponseEntity<Page> paging(@PathVariable(value = "page") int page,
			@PathVariable(value = "number") int number) {

		return new ResponseEntity<Page>(personnelServerI.Paging(page, number), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public ResponseEntity<List<Personnel>> listAll_p() {
		List<Personnel> listContact = (List<Personnel>) personnelRepository.findAll();
		if (listContact.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Personnel>>(listContact, HttpStatus.OK);
	}

	@RequestMapping(value = "/manage/{id}/all/personnel", method = RequestMethod.GET)
	public ResponseEntity<ReturnObject> ALL_personnel(@PathVariable(value = "id") int id) {
		return new ResponseEntity<ReturnObject>(personnelServerI.find_manage_p(id), HttpStatus.OK);
	}

	/* Them user */
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<ApiResponse> ADD(@Valid @RequestBody Personnel personnel) {

		return new ResponseEntity<ApiResponse>(personnelServerI.addPersonnel(personnel), HttpStatus.OK);

	}

	/* update */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> Update(@Valid @RequestBody Personnel personnel,
			@PathVariable(value = "id") int id) {

		return new ResponseEntity<ApiResponse>(personnelServerI.UpdatePersonnel(personnel, id), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/Passwork/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> Update(@Valid @RequestBody User user, @PathVariable(value = "id") int id) {

		return new ResponseEntity<ApiResponse>(userServerI.ChangePassword(user, id), HttpStatus.OK);

	}

	/* decentralization of user */
	@RequestMapping(value = "/user/decentralization", method = RequestMethod.POST)
	public ResponseEntity<ReturnObject> ADDdecentralization(@Valid @RequestBody User_Role user_Role) {
		user_RoleRepository.save(user_Role);
		return new ResponseEntity<ReturnObject>(new ReturnObject(0, "success", user_Role), HttpStatus.OK);

	}

	@RequestMapping(value = "/user/decentralization/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deletedecentralization(@PathVariable(value = "id") int id) {

		User_Role user_Role = user_RoleRepository.getOne(id);
		if (user_Role == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(1, "ERROR in ID"), HttpStatus.BAD_REQUEST);
		}
		user_RoleRepository.deleteById(id);

		return new ResponseEntity<ApiResponse>(new ApiResponse(0, "SUCCESS DELETE decentralization"), HttpStatus.OK);

	}

	@RequestMapping(value = "/user/decentralization/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ApiResponse> updatedecentralization(@Valid @RequestBody User_Role user_Roles,
			@PathVariable(value = "id") int id) {

		User_Role user_Role = user_RoleRepository.getOne(id);
		if (user_Role == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(1, "ERROR in ID"), HttpStatus.BAD_REQUEST);
		} else {
			user_Role.setRole_id(user_Roles.getRole_id());
			user_RoleRepository.save(user_Role);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse(0, "SUCCESS UPDATE decentralization"), HttpStatus.OK);

	}

	/* xoa user */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> delete(@PathVariable(value = "id") int id) {
		User user = userRepository.getOne(id);
		if (user == null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(0, "ERROR in ID"), HttpStatus.BAD_REQUEST);
		}

		userRepository.delete(user);
		return new ResponseEntity<ApiResponse>(new ApiResponse(1, "success delete"), HttpStatus.OK);
	}

	@RequestMapping(value = "/personnel/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ApiResponse> deletepersonnel(@PathVariable(value = "id") int id) {

		return new ResponseEntity<ApiResponse>(personnelServerI.Delete_personnel(id), HttpStatus.OK);
	}

}
