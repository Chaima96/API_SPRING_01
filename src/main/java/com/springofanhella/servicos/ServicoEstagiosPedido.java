package com.springofanhella.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.enums.Estados_Pedidos;
import com.springofanhella.excecao.NaoEncontradoException;
import com.springofanhella.modelo.PageModel;
import com.springofanhella.modelo.PageRequestModel;
import com.springofanhella.repositorio.RepositorioEstagiosPedido;
import com.springofanhella.repositorio.RepositorioPedido;

@Service
public class ServicoEstagiosPedido {

	@Autowired private RepositorioEstagiosPedido servicoEP;
	@Autowired private RepositorioPedido servicoP;
	
	public Estagios_Pedidos salvar(Estagios_Pedidos ep) {
		
		ep.setDataRealizacao(new Date());
		Estagios_Pedidos epcriado =  servicoEP.save(ep);
		Long requestId = ep.getPedido().getId();
		Estados_Pedidos estado = ep.getEstadosPedido();
		servicoP.upDateEstado(requestId, estado);
		return epcriado;
	}
	
	public Estagios_Pedidos obterById(Long id) {
		
		Optional<Estagios_Pedidos> result = servicoEP.findById(id);
		return result.orElseThrow(() -> new NaoEncontradoException("Nao existe Estagio do Pedido com ID = " + id));
	}
	
	public List<Estagios_Pedidos> listarTdosByPedidoId(Long id) {
		
		List<Estagios_Pedidos> lista = servicoEP.findAllByPedidoId(id);
		return lista;
	}
	

	public PageModel<Estagios_Pedidos> listarTdosByPedidoIdOnLazzyMode(Long id, PageRequestModel  pr) {
		
		Pageable pageable = PageRequest.of(pr.getPagina(), pr.getTamanho());
		Page<Estagios_Pedidos> page = servicoEP.findAllByPedidoId(id, pageable);
		
		PageModel<Estagios_Pedidos> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
