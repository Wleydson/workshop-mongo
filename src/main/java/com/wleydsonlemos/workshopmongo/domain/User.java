package com.wleydsonlemos.workshopmongo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String name;
	
	private String email;
}
