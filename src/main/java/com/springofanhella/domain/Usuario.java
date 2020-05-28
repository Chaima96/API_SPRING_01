package com.springofanhella.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springofanhella.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Usamos as anotacoes  da Biblioteca Lombok para fazermos declaracoes implissitas de metodos e construtores
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter 

// uso das anotacoes do JPA (java Persistent API) para persistencia dos objectos Java no Banco de Dados
@Entity(name = "usuarios")
// Implementamos a interface Serialisable para fazer convercao dos objectos em byte 
public class Usuario implements Serializable {
	
   /**
     * variavel de control que serve para verificar se o objecto
	 * que iremos receber a sua versao e compativel com a versao
	 * com a classe utilizada durante a serializacao
	*/
	private static final long serialVersionUID = 1L;

// Declaracao dos atributos do usuario 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String nome;
	
	@Column(length = 75, nullable = false, unique = true)
	private String email;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__(@JsonProperty))
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos = new ArrayList<Pedido>(); // Este atributo permite que usuario tenha uma lista de pedidos
	
	@Getter(onMethod = @__({@JsonIgnore}))
	@OneToMany(mappedBy = "usuario")
	private List<Estagios_Pedidos> estagios = new ArrayList<Estagios_Pedidos>();
	

}
