package com.wleydsonlemos.workshopmongo.resources;

import static com.wleydsonlemos.workshopmongo.util.Util.decodeParam;
import static com.wleydsonlemos.workshopmongo.util.Util.convertDate;

import java.util.Date;
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
	
	@GetMapping(value="/search")
 	public ResponseEntity<List<Post>> searchParam(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate
 			) {
		
		text = decodeParam(text);
		Date min = convertDate(minDate, new Date(0L));
		Date max = convertDate(maxDate, new Date());
		
		List<Post> list = service.searchParam(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
