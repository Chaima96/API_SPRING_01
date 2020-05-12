package com.springofanhella.recurso;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springofanhella.domain.Usuario;
import com.springofanhella.dto.UsuarioLoginDTO;
import com.springofanhella.servicos.ServicoUsuario;

@RestController
@RequestMapping(value = "usuarios")
public class RecursoUsuario {

	@Autowired private ServicoUsuario servico;
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario u) {
		
		Usuario ucriado = servico.salvar(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(ucriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizar( @PathVariable(name = "id") Long id, @RequestBody Usuario u) {
		
		u.setId(id);
		Usuario uactualizado = servico.actualizar(u);
		return ResponseEntity.ok(uactualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> ObterById(@PathVariable(name = "id") Long id) {
		
		Usuario u = servico.obterById(id);
		return ResponseEntity.ok(u);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarAll() {
		
		List<Usuario> lista = servico.listarTodos();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody UsuarioLoginDTO u) {
		
		Usuario ulogged = servico.login(u.getEmail(), u.getPassword());
		return ResponseEntity.ok(ulogged);
	}
}