package com.wleydsonlemos.workshopmongo.service;

import static com.wleydsonlemos.workshopmongo.util.Util.plusDay;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wleydsonlemos.workshopmongo.domain.Post;
import com.wleydsonlemos.workshopmongo.repository.PostRepository;
import com.wleydsonlemos.workshopmongo.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post not found"));
	}
	
	public List<Post> findByTitle(String title) {
		return repository.findByTitleContainingIgnoreCase(title);
	}
	

	public List<Post> searchParam(String text, Date minDate, Date maxDate){
		maxDate = plusDay(maxDate, 1);
		return repository.search(text, minDate, maxDate);
	}
	
}
