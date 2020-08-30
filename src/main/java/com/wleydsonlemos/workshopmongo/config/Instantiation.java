package com.wleydsonlemos.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.wleydsonlemos.workshopmongo.domain.Post;
import com.wleydsonlemos.workshopmongo.domain.User;
import com.wleydsonlemos.workshopmongo.dto.AuthorDTO;
import com.wleydsonlemos.workshopmongo.repository.PostRepository;
import com.wleydsonlemos.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private SimpleDateFormat sdf;
	
	@Override
	public void run(String... args) throws Exception {
		BeforeRun();	

		User maria =  User.builder().name("Maria Brown").email("maria@gmail.com").build(); 
		User alex = User.builder().name("Alex Green").email("alex@gmail.com").build();
		User bob = User.builder().name("Bob Grey").email("bob@gmail.com").build();
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria)); 
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
	
	public void BeforeRun() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	
		deleteBase();
	}
	
	public void deleteBase() {
		postRepository.deleteAll();
		userRepository.deleteAll();
	}

}
