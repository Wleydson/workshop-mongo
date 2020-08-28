package com.wleydsonlemos.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.wleydsonlemos.workshopmongo.domain.User;
import com.wleydsonlemos.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		
		User maria =  User.builder().name("Maria Brown").email("maria@gmail.com").build(); 
		User alex = User.builder().name("Alex Green").email("alex@gmail.com").build();
		User bob = User.builder().name("Bob Grey").email("bob@gmail.com").build();
		
		repository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
