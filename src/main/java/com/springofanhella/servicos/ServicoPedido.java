package com.springofanhella.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.enums.Estados_Pedidos;
import com.springofanhella.excecao.NaoEncontradoException;
import com.springofanhella.modelo.PageModel;
import com.springofanhella.modelo.PageRequestModel;
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
	
     public PageModel<Pedido> listAllOnLazzyMode(PageRequestModel pr) {
		
		Pageable pageable = PageRequest.of(pr.getPagina(), pr.getTamanho());
		Page<Pedido> page = servicoP.findAll(pageable);
		
		PageModel<Pedido> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
	
	public List<Pedido> ObterTdosByUsuarioId(Long id) {
		
		List<Pedido> lista = servicoP.findAllByUsuarioId(id);
		return lista;
	}
	
	public PageModel<Pedido> ObterTdosByUsuarioIdOnLazzyMode(Long id, PageRequestModel  pr) {
		
		Pageable pageable = PageRequest.of(pr.getPagina(), pr.getTamanho());
		Page<Pedido> page = servicoP.findAllByUsuarioId(id, pageable);
		
		PageModel<Pedido> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
