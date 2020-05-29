package com.springofanhella.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springofanhella.domain.Usuario;
import com.springofanhella.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RepositorioUsuarioTests {

	@Autowired private RepositorioUsuario repositorioUsuario;
	
	
	@Ignore
	public void AsalvarTest() {
		
		Usuario usuario = new Usuario(null, "Lucas", "lucaschaima@gmail.com", "12345", Role.ADMINISTRADOR, null, null);
		Usuario usuarioCriado = repositorioUsuario.save(usuario);
		
		assertThat(usuarioCriado.getId()).isEqualTo(1L);
	}
	
	@Ignore
	public void updateTest() {
		
		Usuario usuario = new Usuario(2L, "Lucas Albino Chaima", "lucaschaima@gmail.com", "12345", Role.ADMINISTRADOR, null, null);
		Usuario usuarioActalizado = repositorioUsuario.save(usuario);
		
		assertThat(usuarioActalizado.getNome()).isEqualTo("Lucas Albino Chaima");
	}
	
	@Ignore
	public void getByIdTest() {
		
		Optional<Usuario> resultado = repositorioUsuario.findById(2L);
		Usuario usuario = resultado.get();
		
		assertThat(usuario.getPassword()).isEqualTo("12345");
		
	}
	
	@Ignore
	public void listarTest() {
		
		List<Usuario> usuarios = repositorioUsuario.findAll();
		
		assertThat(usuarios.size()).isEqualTo(1);
	}
	
	@Ignore
	public void loginTest() {
		
		Optional<Usuario> resultado = repositorioUsuario.login("lucaschaima@gmail.com", "12345");
		Usuario usuarioLogado = resultado.get();
		
		assertThat(usuarioLogado.getId()).isEqualTo(2L);
	}
	
	@Test
	public void updateRoleTest() {
		
		int linhasAfetadas = repositorioUsuario.updateRole(4L, Role.ADMINISTRADOR);
		assertThat(linhasAfetadas).isEqualTo(1);
	}
}
