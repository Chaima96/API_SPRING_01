package com.springofanhella.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springofanhella.domain.enums.Estados_Pedidos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Usamos as anotacoes  da Biblioteca Lombok para fazermos declaracoes implissitas de metodos e construtores
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

//uso das anotacoes do JPA (java Persistent API) para persistencia dos objectos Java no Banco de Dados
@Entity(name = "Estagios_pedidos")
public class Estagios_Pedidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "text")
	private String descricao;
	
	@Column(name = "data_realizacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRealizacao;
	
	@Column(name = "estado_pedido", length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private Estados_Pedidos estadosPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido", nullable = false)
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

}
