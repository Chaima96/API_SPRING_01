package com.springofanhella.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Usamos as anotacoes  da Biblioteca Lombok para fazermos declaracoes implissitas de metodos e construtores
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

public class Pedido {
	
	private Long id;
	private String assunto;
	private String descricao;
	private Date dataCriacao;
	private Estagios_Pedidos estagio;
	private Usuario usuario;
	private List<Estagios_Pedidos> estagiosPedido = new ArrayList<Estagios_Pedidos>();

}
