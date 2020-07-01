package com.springofanhella.recurso;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.Pedido;
import com.springofanhella.dto.PedidoUpdateDTO;
import com.springofanhella.dto.PedidosaveDTO;
import com.springofanhella.modelo.PageModel;
import com.springofanhella.modelo.PageRequestModel;
import com.springofanhella.servicos.ServicoEstagiosPedido;
import com.springofanhella.servicos.ServicoPedido;

@RestController
@RequestMapping(value = "pedidos")
public class RecursoPedido {

	@Autowired private ServicoPedido servico;
	@Autowired private ServicoEstagiosPedido servicoEP;
	
	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody @Valid PedidosaveDTO pdto) {
		
		Pedido p = pdto.transformToP();
		Pedido pcriado = servico.salvar(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(pcriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> actualizado(@PathVariable(name = "id") Long id, @RequestBody @Valid PedidoUpdateDTO pdto) {
		
		Pedido p = pdto.transformToP();
		p.setId(id);
		Pedido pactualiozado = servico.actualizar(p);
		return ResponseEntity.ok(pactualiozado);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Pedido> ObterById(@PathVariable(name = "id") Long id) {
		
		Pedido p = servico.obterById(id);
		return ResponseEntity.ok(p);
	}
	
	/*
	@GetMapping
	public ResponseEntity<List<Pedido >> listaAll() {
		
		List<Pedido> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	*/
	
	@GetMapping
	public ResponseEntity<PageModel<Pedido>> listarAll(
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho ) {
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Pedido> pm = servico.listAllOnLazzyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	/*
	@GetMapping("/{id}/estagios")
	public ResponseEntity<List<Estagios_Pedidos>> listaAllEstagiosById(@PathVariable(name = "id") Long id) {
		
		List<Estagios_Pedidos> listaEstagios = servicoEP.listarTdosByPedidoId(id);
		return ResponseEntity.ok(listaEstagios);
	}
	*/
	
	@GetMapping("/{id}/estagios")
	public ResponseEntity<PageModel<Estagios_Pedidos>> listaAllEstagiosById(
			@PathVariable(name = "id") Long id,
			@RequestParam(value = "pagina", defaultValue = "0") int pagina, 
			@RequestParam(value = "tamanho", defaultValue = "10") int tamanho) {
		
		PageRequestModel pr = new PageRequestModel(pagina, tamanho);
		PageModel<Estagios_Pedidos> pm = servicoEP.listarTdosByPedidoIdOnLazzyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
	
}
