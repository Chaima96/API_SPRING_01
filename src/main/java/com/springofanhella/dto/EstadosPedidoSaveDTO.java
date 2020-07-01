package com.springofanhella.dto;

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
@NoArgsConstructor
@AllArgsConstructor
public class EstadosPedidoSaveDTO {

	private String descricao;
	
	@NotNull(message = "Pedido e Obrigatorio")
	private Pedido pedido;
	
	@NotNull(message = "Estado e Obrigatorio")
	private Estados_Pedidos estado;
	
	@NotNull(message = "Usuario e Obrigatorio")
	private Usuario usuario;
	
	public Estagios_Pedidos transformToEs() {
		
		Estagios_Pedidos estagios = new Estagios_Pedidos(null, descricao, null, this.estado, this.pedido, this.usuario);
		return estagios;
	}
	
}
