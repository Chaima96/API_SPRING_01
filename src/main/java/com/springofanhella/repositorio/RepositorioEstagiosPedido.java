package com.springofanhella.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springofanhella.domain.Estagios_Pedidos;


/**
 * @author Chaima
 * @Repository Anotacao que identifica a interface como um repositorio
 * Para que possamos ter a produtividade do JPA e ter alguns metodos
 * prontos de acesso a dados estendemos a interface JpaRepositor
 */

@Repository
public interface RepositorioEstagiosPedido extends JpaRepository<Estagios_Pedidos, Long> {

	public List<Estagios_Pedidos> findAllByPedidoId(Long id);
	public Page<Estagios_Pedidos> findAllByPedidoId(Long id, Pageable pageable);
	
}
