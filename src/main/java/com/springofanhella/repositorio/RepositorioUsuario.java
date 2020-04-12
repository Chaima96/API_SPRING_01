package com.springofanhella.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springofanhella.domain.Usuario;

/**
 * @author Chaima
 * @Repository Anotacao que identifica a interface como um repositorio
 * Para que possamos ter a produtividade do JPA e ter alguns metodos
 * prontos de acesso a dados estendemos a interface JpaRepositor
 */

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>  {
	
	
	@Query("SELECT FROM usuarios WHERE email = ?1 AND password = ?2")
	public Optional<Usuario> login(String email, String password);

}
