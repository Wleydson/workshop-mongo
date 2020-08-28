package com.wleydsonlemos.workshopmongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wleydsonlemos.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>>  findAll(){
		User user1 = User.builder().id("1").name("test1").email("1@email.com").build();
		User user2 = User.builder().id("2").name("test2").email("2@email.com").build();

		return ResponseEntity.ok().body(Arrays.asList(user1, user2));
	}
	
}
