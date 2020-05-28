package com.springofanhella.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.servicos.ServicoEstagiosPedido;

@RestController
@RequestMapping(value = "estagios")
public class RecursoEstagioPedido {

	@Autowired private ServicoEstagiosPedido servico;
	
	@PostMapping
	public ResponseEntity<Estagios_Pedidos> salvar(@RequestBody Estagios_Pedidos ep) {
		
		Estagios_Pedidos epcriado = servico.salvar(ep);
		return ResponseEntity.status(HttpStatus.CREATED).body(epcriado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estagios_Pedidos> ObterById(@PathVariable(name = "id") Long id) {
		
		Estagios_Pedidos ep = servico.obterById(id);
		return ResponseEntity.ok(ep);
	}
	
	
	
}

