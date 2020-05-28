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

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.Pedido;
import com.springofanhella.servicos.ServicoEstagiosPedido;
import com.springofanhella.servicos.ServicoPedido;

@RestController
@RequestMapping(value = "pedidos")
public class RecursoPedido {

	@Autowired private ServicoPedido servico;
	@Autowired private ServicoEstagiosPedido servicoEP;
	
	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido p) {
		
		Pedido pcriado = servico.salvar(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(pcriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> actualizado(@PathVariable(name = "id") Long id, @RequestBody Pedido p) {
		p.setId(id);
		Pedido pactualiozado = servico.actualizar(p);
		return ResponseEntity.ok(pactualiozado);
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Pedido> ObterById(@PathVariable(name = "id") Long id) {
		
		Pedido p = servico.obterById(id);
		return ResponseEntity.ok(p);
	}
	
	@GetMapping
	public ResponseEntity<List<Pedido >> listaAll() {
		
		List<Pedido> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}/estagios")
	public ResponseEntity<List<Estagios_Pedidos>> listaAllEstagiosById(@PathVariable(name = "id") Long id) {
		
		List<Estagios_Pedidos> listaEstagios = servicoEP.listarTdosByPedidoId(id);
		return ResponseEntity.ok(listaEstagios);
	}
	
}
