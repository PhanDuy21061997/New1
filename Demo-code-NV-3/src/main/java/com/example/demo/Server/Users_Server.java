package com.example.demo.Server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.domain.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.util.CryptWithMD5;

@Service
public class Users_Server  implements Users_ServerI {

	@Autowired
	UserRepository userRepository ;
	@Override
	public boolean findUserName(String Username) {
		
		if (userRepository.find_username(Username)==null) {
			return true;
		}
		return false;
	}
	@Override
	public ApiResponse ChangePassword(User user, int id) {
		User usertest=userRepository.getOne(id);
		if(user==null)
		{
			return new ApiResponse(1,"ERROR does not exist id");
		}
		else {
			usertest.setPasswork(new CryptWithMD5().cryptWithMD5(user.getPasswork()));
			userRepository.save(usertest);
		}
		
		return new ApiResponse(0,"Success ChangePassword");
	}

}
