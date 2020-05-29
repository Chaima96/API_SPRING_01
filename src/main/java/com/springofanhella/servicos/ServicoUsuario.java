package com.springofanhella.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springofanhella.domain.Usuario;
import com.springofanhella.excecao.NaoEncontradoException;
import com.springofanhella.repositorio.RepositorioUsuario;
import com.springofanhella.servicos.util.HashUtil;

@Service
public class ServicoUsuario {

	/**
	 * A classe servico sera responsavel 
	 * por implementas os metodos da interface 
	 * repositor
	 */
	
	@Autowired private RepositorioUsuario servicoU;
	
	public Usuario salvar(Usuario u) {
		
		String hash = HashUtil.getSecureHash(u.getPassword());
		u.setPassword(hash);
		Usuario ucriado = servicoU.save(u);
		return ucriado;
	}
	
	public Usuario actualizar(Usuario u) {
		
		String hash = HashUtil.getSecureHash(u.getPassword());
		u.setPassword(hash);
		Usuario uactualizado = servicoU.save(u);
		return uactualizado;
	}
	
	public Usuario obterById(Long id) {
		
		Optional<Usuario> result = servicoU.findById(id);
		return result.orElseThrow(() -> new NaoEncontradoException("Nao existe usuario com ID = " + id));
	}
	
	public List<Usuario> listarTodos() {
		
		List<Usuario> lista = servicoU.findAll();
		return lista;
	}
	
	public Usuario login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<Usuario> u = servicoU.login(email, password);
		return u.get();
	}
}
