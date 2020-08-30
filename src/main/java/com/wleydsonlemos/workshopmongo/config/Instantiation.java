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
import com.wleydsonlemos.workshopmongo.dto.CommentDTO;
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

		
		Post post1 = Post.builder().date(sdf.parse("21/08/2020")).title("Partiu viagem").body("Vou viajar para São Paulo. Abraços!").author(new AuthorDTO(maria)).build();
		Post post2 = Post.builder().date(sdf.parse("23/08/2020")).title("Bom dia").body("Acordei feliz hoje!").author(new AuthorDTO(maria)).build();

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/08/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/08/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/08/2020"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.setPosts(Arrays.asList(post1, post2));
		userRepository.save(maria);
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
