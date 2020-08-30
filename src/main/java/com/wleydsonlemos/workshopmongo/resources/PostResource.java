package com.wleydsonlemos.workshopmongo.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wleydsonlemos.workshopmongo.domain.Post;
import com.wleydsonlemos.workshopmongo.service.PostService;

import lombok.RequiredArgsConstructor;

import static com.wleydsonlemos.workshopmongo.resources.util.URL.decodeParam;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	private final PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		
		return ResponseEntity.ok().body( post );
	}
	
	@GetMapping(value = "/title")
	public ResponseEntity< List<Post> > findByTitle(@RequestParam(value = "text", defaultValue = "") String title){
		String titleDecode = decodeParam(title);
		List<Post> posts = service.findByTitle(titleDecode);
		
		return ResponseEntity.ok().body( posts );
	}
	
}
