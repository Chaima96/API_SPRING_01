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

import com.springofanhella.domain.Estagios_Pedidos;
import com.springofanhella.domain.Pedido;
import com.springofanhella.domain.Usuario;
import com.springofanhella.domain.enums.Estados_Pedidos;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RepositorioEstagioPedidoTest {

	@Autowired private RepositorioEstagiosPedido estagios;
	
	@Test
	public void AsalvarTest() {
		Usuario u = new Usuario();
		u.setId(3L);
		
		Pedido p = new Pedido();
		p.setId(3L);
		Estagios_Pedidos estagio = new Estagios_Pedidos(null, "foi comprado novo laptop", new Date(), Estados_Pedidos.FECHADO, p, u);
		
		Estagios_Pedidos estagiocriado = estagios.save(estagio);
		
		assertThat(estagiocriado.getId()).isEqualTo(4L);
	}
	
	@Test
    public void getByIdTest() {
		
    	Optional<Estagios_Pedidos> resultado = estagios.findById(4L);
    	Estagios_Pedidos estagios = resultado.get();
    	
    	assertThat(estagios.getDescricao()).isEqualTo("foi comprado novo laptop"); 
	}
    
	@Test
    public void listarByEstadoIdTest() {
		
    	List<Estagios_Pedidos> lista = estagios.findAllByPedidoId(3L);
    	
    	assertThat(lista.size()).isEqualTo(1); 
    	
	}
}
