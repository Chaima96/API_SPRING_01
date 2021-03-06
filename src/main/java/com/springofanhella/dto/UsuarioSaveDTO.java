package com.springofanhella.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.Usuario;
import com.springofanhella.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UsuarioSaveDTO {

	@NotBlank(message = "nome e obrigatorio")
	private String nome;
	
	@Email(message = "email invalido")
	private String email;
	
	@Size(min = 5, max = 99, message = "A password deve conter caracteres entes 5 a 99")
	private String password;
	
	@NotNull(message = "obrigatorio")
	private Role role;
	
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Estagios_Pedidos> estagios = new ArrayList<Estagios_Pedidos>();
	
	public Usuario transforToUser() {
		
		Usuario u = new Usuario(null, this.nome, this.email, this.password, this.role, this.pedidos, this.estagios);
		return u;
	}
}
