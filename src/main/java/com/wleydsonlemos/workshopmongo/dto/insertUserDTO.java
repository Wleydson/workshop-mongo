package com.wleydsonlemos.workshopmongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class insertUserDTO {

	private String name;
	
	private String email;
}
