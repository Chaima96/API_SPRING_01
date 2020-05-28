package com.springofanhella.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.Usuario;
import com.springofanhella.domain.enums.Estados_Pedidos;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RepositorioPedidoTests {

	@Autowired RepositorioPedido respositorioPedido;
	
	@Test
	public void AsalvarTest() {
		
		Usuario usuario = new Usuario();
		usuario.setId(4L);
		Pedido pedido = new Pedido(null, "Novo LapTop Hp", "Predendo Obter Novo PC HP", new Date(), Estados_Pedidos.ABERTO, usuario, null);
		Pedido novoPedido = respositorioPedido.save(pedido);
		
		assertThat(novoPedido.getId()).isEqualTo(1L);
		
	}
	
	@Test
	public void updateTest() {
		
		Usuario usuario = new Usuario();
		usuario.setId(4L);
		Pedido pedido = new Pedido(1L, "Novo LapTop Hp", "Predendo Obter Novo PC HP Pavilion", null, Estados_Pedidos.ABERTO, usuario, null);
		Pedido PedidoActualizado = respositorioPedido.save(pedido);
		
		assertThat(PedidoActualizado.getDescricao()).isEqualTo("Predendo Obter Novo PC HP Pavilion");
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<Pedido> result = respositorioPedido.findById(1L);
		Pedido pedido = result.get();
		
		assertThat(pedido.getAssunto()).isEqualTo("Novo LapTop Hp");
	}
	
	@Test
	public void listarTest() {
		
		List<Pedido> pedidos = respositorioPedido.findAll();
		
		assertThat(pedidos.size()).isEqualTo(1);
	}
	
	@Test
    public void listarByUsuarioIdTest() {
		
		List<Pedido> pedidos = respositorioPedido.findAllByUsuarioId(2L);
		
		assertThat(pedidos.size()).isEqualTo(1);
	}
	
	@Test
	public void updateEstadoTest() {
		
		int linhasAfectadas = respositorioPedido.upDateEstado(1L, Estados_Pedidos.EMPROCESSO);
		
		assertThat(linhasAfectadas).isEqualTo(1);
	}
}
