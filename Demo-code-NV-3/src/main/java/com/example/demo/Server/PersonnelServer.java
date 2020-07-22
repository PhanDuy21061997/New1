package com.example.demo.Server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ManageRepository;
import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.domain.ApiResponse;
import com.example.demo.dto.Page;
import com.example.demo.model.Personnel;
import com.example.demo.model.User;
import com.example.demo.util.CheckObject;
import com.example.demo.util.CryptWithMD5;
import com.example.demo.util.EmailValidator;
import com.example.demo.util.Reporter;
import com.example.demo.util.ReturnObject;
import com.example.demo.util.TokenGenerator;

import lombok.experimental.var;

@Service
public class PersonnelServer implements PersonnelServerI {

	@Autowired
	PersonnelRepository personnelRepository;
	@Autowired
	ManageRepository manageRepository;
	/*
	 * @Autowired UserServerI userServerI ;
	 */

	@Override
	public Personnel login(String username, String passwork) {
		// TODO Auto-generated method stub

		try {
			personnelRepository.login(username, passwork);
		} catch (Exception e) {
			Reporter.getErrorLogger().error("personnelRepository.login(username, passwork)", e);
		}
		return personnelRepository.login(username, passwork);
	}

	@Override
	public ReturnObject find_manage_p(int id_manage_p) {
		List<Personnel> list = new ArrayList<Personnel>();
		list = personnelRepository.find_manage_p(id_manage_p);
		if (list.size() == 0) {
			return new ReturnObject(1, " ERROR", null);
		}
		return new ReturnObject(0, " SUCCESS", list);

	}

	@Override
	public ReturnObject loginAPI(String username, String passwork) {

		String md5_passwork = new CryptWithMD5().cryptWithMD5(passwork);
		Personnel personnel = personnelRepository.login(username, md5_passwork);

		if (personnel == null) {
			return new ReturnObject(0, "Error LOGIN", null);

		}
		return new ReturnObject(1, "success", personnel);
	}

	@Override
	public ApiResponse addPersonnel(Personnel personnel) {
		User user = personnel.getUser();
		user.setPasswork(new CryptWithMD5().cryptWithMD5(user.getPasswork()));
		boolean email = new EmailValidator().validate(personnel.getEmail());
		if (personnel.getAddress() == null || personnel.getEmail() == null || personnel.getName() == null
				|| email == false) {
			return new ApiResponse(0, "ERROR");
		}
		/*
		 * else if (userServerI.findUserName(user.getUsername())==false) { return new
		 * ApiResponse(0, "ERROR Exist UserName"); }
		 */
		else {
			personnelRepository.save(personnel);
		}

		return new ApiResponse(1, "SUCCESS");
	}

	@Override
	public ApiResponse UpdatePersonnel(Personnel personnel, int id) {
		Personnel personnel2 = personnelRepository.getOne(id);
		if (personnel.getAddress() == null || personnel.getAddress() == null || personnel2 == null) {
			return new ApiResponse(1, "ERROR NULL");
		}
		if (new EmailValidator().validate(personnel.getEmail()) == false) {
			return new ApiResponse(1, "ERROR EMAIL");
		} else {
			personnel2.setDate_of_birth(personnel.getDate_of_birth());
			personnel2.setEmail(personnel.getEmail());
			personnel2.setAddress(personnel.getAddress());
			personnel2.setName(personnel.getName());
			personnelRepository.save(personnel2);
		}

		return new ApiResponse(0, "SUCCESS");
	}

	@Override
	public ReturnObject GetuserID(int id) {
		Personnel personnel = personnelRepository.getOne(id);
		Personnel personnel1 = new Personnel(personnel.getId_p(), personnel.getName(), personnel.getAddress(),
				personnel.getEmail(), personnel.getDate_of_birth());

		if (personnel == null) {
			return new ReturnObject(1, "Error does not exist", null);
		}
		return new ReturnObject(0, "SUCCESS", personnel1);
	}

	@Override
	public Page Paging(int page, int number) {
		int total_number = personnelRepository.totalNumber();
		int total_page = total_number / number;
		List<Personnel> list = new ArrayList<Personnel>();
		int someOffset = 0;
		if (page > 0 && page <= total_page && page > 0 && number > 0) {
			if (page > 1) {
				someOffset = (page * number) - (number);
			}
		} else {
			return new Page(0, 0, 0, new ApiResponse(1, "ERROR page "));
		}

		list = personnelRepository.Paging(number, someOffset);
		return new Page(number, page, total_page, list);
	}

	@Override
	public ApiResponse Delete_personnel(int id) {
		Personnel personnel = personnelRepository.getOne(id);
		if (!(manageRepository.check_id_manage(id))) {
			return new ApiResponse(1, "ERROR  delete");
		} else if (personnel == null) {
			return new ApiResponse(0, "ERROR in ID");
		}
		personnelRepository.deleteById(id);
		return new ApiResponse(0, "success delete");
	}

}
