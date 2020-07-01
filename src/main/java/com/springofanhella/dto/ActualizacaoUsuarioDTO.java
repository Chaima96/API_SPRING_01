package com.springofanhella.dto;

import javax.validation.constraints.NotNull;

import com.springofanhella.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizacaoUsuarioDTO {

	@NotNull(message = "Role obrigatorio")
	private Role role;
}
