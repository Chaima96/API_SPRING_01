package com.springofanhella.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.springofanhella.domain.Pedido;

/**
 * @author Chaima
 * @Repository Anotacao que identifica a interface como um repositorio
 * Para que possamos ter a produtividade do JPA e ter alguns metodos
 * prontos de acesso a dados estendemos a interface JpaRepositor
 */

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findAllByUsuarioId(Long id);
	
}
