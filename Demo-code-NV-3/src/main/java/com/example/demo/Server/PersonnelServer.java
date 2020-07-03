package com.example.demo.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.PersonnelRepository;
import com.example.demo.domain.ApiResponse;
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
	public List<Personnel> find_manage_p(int id_manage_p) {
		// TODO Auto-generated method stub
		return null;
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
		} else {
			personnelRepository.save(personnel);
		}

		return new ApiResponse(1, "SUCCESS");
	}

}
