package com.springofanhella.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.enums.Estados_Pedidos;

/**
 * @author Chaima
 * @Repository Anotacao que identifica a interface como um repositorio
 * Para que possamos ter a produtividade do JPA e ter alguns metodos
 * prontos de acesso a dados estendemos a interface JpaRepositor
 */

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findAllByUsuarioId(Long id);
	public Page<Pedido> findAllByUsuarioId(Long id, Pageable pageable);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE pedidos SET estadosPedido = ?2 WHERE id = ?1")
	public int upDateEstado(Long id, Estados_Pedidos estado);
	
}
