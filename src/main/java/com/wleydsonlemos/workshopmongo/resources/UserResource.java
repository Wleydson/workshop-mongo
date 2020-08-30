package com.wleydsonlemos.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wleydsonlemos.workshopmongo.domain.Post;
import com.wleydsonlemos.workshopmongo.domain.User;
import com.wleydsonlemos.workshopmongo.dto.UserDTO;
import com.wleydsonlemos.workshopmongo.dto.insertUserDTO;
import com.wleydsonlemos.workshopmongo.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	private final UserService service;
	private final ModelMapper modelMapper;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usersDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		
		return ResponseEntity.ok().body( modelMapper.map(user, UserDTO.class));
	}
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User user = service.findById(id);
		
		return ResponseEntity.ok().body( user.getPosts() );
	}
	
	@PostMapping
	public ResponseEntity<Void> insertUser(@RequestBody insertUserDTO dto){
		User user = modelMapper.map(dto, User.class);
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDTO> deleteById(@PathVariable String id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody insertUserDTO dto){
		User user = modelMapper.map(dto, User.class);
		user.setId(id);
		
		service.update(user);
		return ResponseEntity.noContent().build();
	}
}
