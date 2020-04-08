package com.springofanhella.domain;

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
public class Estagios_Pedidos {
	
	private Long id;
	private String descricao;
	private Date dataRealizacao;
	private Estagios_Pedidos estagioPedido;
	private Pedido pedido;
	private Usuario usuario;

}
