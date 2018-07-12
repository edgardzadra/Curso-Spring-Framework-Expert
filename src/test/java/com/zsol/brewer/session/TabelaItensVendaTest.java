package com.zsol.brewer.session;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.zsol.brewer.model.Cerveja;
import com.zsol.brewer.session.TabelaItensVenda;

public class TabelaItensVendaTest {
	
	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp(){
		this.tabelaItensVenda = new TabelaItensVenda("1");
	}
	
	@Test
	public void deveCalcularTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("9.90");
		cerveja.setValor(valor);
		
		tabelaItensVenda.adicionarItem(cerveja, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularTotalComVariosItens() throws Exception {
		
		Cerveja cerveja = new Cerveja();
		cerveja.setId(1L);
		BigDecimal valor = new BigDecimal("9.90");
		Integer quantidade = 15;
		BigDecimal total1 = valor.multiply(new BigDecimal(quantidade));
		cerveja.setValor(valor);
		
		Cerveja cerveja2 = new Cerveja();
		cerveja2.setId(2L);
		BigDecimal valor2 = new BigDecimal("5.48");
		Integer quantidade2 = 12;
		BigDecimal total2 = valor2.multiply(new BigDecimal(quantidade2));
		cerveja2.setValor(valor2);
		
		tabelaItensVenda.adicionarItem(cerveja, quantidade);
		tabelaItensVenda.adicionarItem(cerveja2, quantidade2);
		
		BigDecimal valorTotal = (total1.add(total2));
		
		assertEquals(valorTotal, tabelaItensVenda.getValorTotal());
	}
	

	@Test
	public void verificaAlteracaoListaIncluirMesmaCerveja() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("4.50");
		cerveja.setId(1L);
		cerveja.setValor(valor);
		
		tabelaItensVenda.adicionarItem(cerveja, 1);
		tabelaItensVenda.adicionarItem(cerveja, 1);
		tabelaItensVenda.adicionarItem(cerveja, 1);
		
		assertEquals(1, tabelaItensVenda.total());
		
	}
	
	@Test
	public void verificaAlteracaoDeQuantidadeItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setId(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.alterQuantidadeItemCadastrado(c1, 3);
		
		assertEquals(new BigDecimal("13.50"), tabelaItensVenda.getValorTotal());
		assertEquals(1, tabelaItensVenda.total());
	}
	
	
	@Test
	public void removeCervejaDaLista() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setId(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		Cerveja c2 = new Cerveja();
		c2.setId(2L);
		c2.setValor(new BigDecimal("10.00"));
		
		Cerveja c3 = new Cerveja();
		c3.setId(3L);
		c3.setValor(new BigDecimal("5.00"));
		
		tabelaItensVenda.adicionarItem(c1, 2);
		tabelaItensVenda.adicionarItem(c2, 3);
		tabelaItensVenda.adicionarItem(c3, 10);
		
		tabelaItensVenda.removerCerveja(c3);
		
		assertEquals(new BigDecimal("39.00"), tabelaItensVenda.getValorTotal());
		assertEquals(2, tabelaItensVenda.total());
	}
}
