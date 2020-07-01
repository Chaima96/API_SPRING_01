package com.springofanhella.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UsuarioLoginDTO {

	@Email(message = "O endereco de Email e invalido tente novamente")
	private String email;
	
	@NotBlank(message = "Obrigatorio")
	private String password;
	
	
}
