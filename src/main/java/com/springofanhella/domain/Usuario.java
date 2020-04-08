package com.springofanhella.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Usamos as anotacoes  da Biblioteca Lombok para fazermos declaracoes implissitas de metodos e construtores
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter 

public class Usuario {
	
// Declaracao dos atributos do usuario 
	
	private Long id;
	private String nome;
	private String email;
	private String password;
	private List<Pedido> pedidos = new ArrayList<Pedido>(); // Este atributo permite que usuario tenha uma lista de pedidos
	private List<Estagios_Pedidos> estagios = new ArrayList<Estagios_Pedidos>();
	

}
