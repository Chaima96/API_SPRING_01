package com.springofanhella.recurso;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.Usuario;
import com.springofanhella.dto.ActualizacaoUsuarioDTO;
import com.springofanhella.dto.UsuarioLoginDTO;
import com.springofanhella.dto.UsuarioSaveDTO;
import com.springofanhella.dto.UsuarioUpdateDTO;
import com.springofanhella.modelo.PageModel;
import com.springofanhella.modelo.PageRequestModel;
import com.springofanhella.servicos.ServicoPedido;
import com.springofanhella.servicos.ServicoUsuario;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "usuarios")
public class RecursoUsuario {

	@Autowired private ServicoUsuario servico;
	@Autowired private ServicoPedido servicoP;
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody @Valid UsuarioSaveDTO udto) {
		
		Usuario usuatioTosave = udto.transforToUser();
		Usuario ucriado = servico.salvar(usuatioTosave);
		return ResponseEntity.status(HttpStatus.CREATED).body(ucriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizar( @PathVariable(name = "id") Long id, @RequestBody @Valid UsuarioUpdateDTO udto) {
		
		Usuario u = udto.transforToUser();
		u.setId(id);
		Usuario uactualizado = servico.actualizar(u);
		return ResponseEntity.ok(uactualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> ObterById(@PathVariable(name = "id") Long id) {
		
		Usuario u = servico.obterById(id);
		return ResponseEntity.ok(u);
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Usuario>> listarAll() {
		
		List<Usuario> lista = servico.listarTodos();
		return ResponseEntity.ok(lista);
	}
	*/
	
	@GetMapping
	public ResponseEntity<PageModel<Usuario>> listarAll(
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho ) {
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Usuario> pm = servico.listAllOnLazzyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody @Valid UsuarioLoginDTO u) {
		
		Usuario ulogged = servico.login(u.getEmail(), u.getPassword());
		return ResponseEntity.ok(ulogged);
	}
	
	/*
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<List<Pedido>> listaAllPedidosById(@PathVariable(name = "id") Long id) {
		
		List<Pedido> listaPedidos = servicoP.ObterTdosByUsuarioId(id);
		return ResponseEntity.ok(listaPedidos);
	}
	*/
	
	@GetMapping("/{id}/pedidos")
	public ResponseEntity<PageModel<Pedido>> listaAllPedidosById(
			@PathVariable(name = "id") Long id, 
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho ) {
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Pedido> pm = servicoP.ObterTdosByUsuarioIdOnLazzyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody @Valid ActualizacaoUsuarioDTO udto) {
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setRole(udto.getRole());
		
		servico.UpdateRole(usuario);
		
		return ResponseEntity.ok().build();
		
	}
}