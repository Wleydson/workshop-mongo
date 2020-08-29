package com.wleydsonlemos.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wleydsonlemos.workshopmongo.domain.User;
import com.wleydsonlemos.workshopmongo.repository.UserRepository;
import com.wleydsonlemos.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
	}
	
	public User insert(User entity) {
		return repository.insert(entity);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}
