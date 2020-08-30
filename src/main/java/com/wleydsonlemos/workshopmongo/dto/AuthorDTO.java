package com.wleydsonlemos.workshopmongo.dto;

import java.io.Serializable;

import com.wleydsonlemos.workshopmongo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;

	public AuthorDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}
	
	
}