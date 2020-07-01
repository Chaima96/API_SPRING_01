package com.springofanhella.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.Usuario;
import com.springofanhella.domain.enums.Estados_Pedidos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoUpdateDTO {

	@NotBlank(message = "Assunto e Obrigatorio")
	private String assunto;
	private String descricao;
	
	@NotNull(message = "Estado e Obrigatorio")
	private Estados_Pedidos estagios;
	
	@NotNull(message = "Usuario e Obrigatorio")
	private Usuario usuario;
	private List<Estagios_Pedidos> estagiosPedido = new ArrayList<Estagios_Pedidos>();
	
	public Pedido transformToP() {
		
		Pedido p = new Pedido(null, this.assunto, this.descricao, null, this.estagios, this.usuario, this.estagiosPedido);
		return p;
	}
}
