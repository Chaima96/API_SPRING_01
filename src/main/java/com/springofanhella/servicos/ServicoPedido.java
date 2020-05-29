package com.springofanhella.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.enums.Estados_Pedidos;
import com.springofanhella.excecao.NaoEncontradoException;
import com.springofanhella.repositorio.RepositorioPedido;

@Service
public class ServicoPedido {

	@Autowired private RepositorioPedido servicoP;
	
	public Pedido salvar(Pedido p) {
		
		p.setEstadosPedido(Estados_Pedidos.ABERTO);
		p.setDataCriacao(new Date());
		Pedido pcriado = servicoP.save(p);
		return pcriado;
	}
	
	public Pedido actualizar(Pedido p) {
		
		Pedido pactualizado = servicoP.save(p);
		return pactualizado;
	}
	
	public Pedido obterById(Long id) {
		
		Optional<Pedido> result = servicoP.findById(id);
		return result.orElseThrow(() -> new NaoEncontradoException("Nao existe Pedido com ID = " + id));
	}
	
	public List<Pedido> obterTodos() {
		
		List<Pedido> lista = servicoP.findAll();
		return lista;
	}
	
	public List<Pedido> ObterTdosByUsuarioId(Long id) {
		
		List<Pedido> lista = servicoP.findAllByUsuarioId(id);
		return lista;
	}
}
