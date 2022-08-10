package com.openclassrooms.poseidon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.openclassrooms.poseidon.model.User;
import com.openclassrooms.poseidon.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	

	public User getUserById(Integer id)  {
		return userRepo.findUserById(id);
	}

	public User saveUser(User user) {
		
		return userRepo.save(user);
	}


	public List<User> findAllUsers() {
		
		return userRepo.findAll();
	}

	
	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}
	
	
}
