package com.springofanhella.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.enums.Estados_Pedidos;
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
		return result.get();
	}
	
	public List<Estagios_Pedidos> listarTdosByPedidoId(Long id) {
		
		List<Estagios_Pedidos> lista = servicoEP.findAllByPedidoId(id);
		return lista;
	}
}
