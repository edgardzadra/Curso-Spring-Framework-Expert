package com.zsol.brewer.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.zsol.brewer.model.Cerveja;
import com.zsol.brewer.model.ItemVenda;

@SessionScope
@Component
public class TabelaItensSession {
	
	private Set<TabelaItensVenda> tabelaItens = new HashSet<>();

	
	
	public void adicionarItem(String uuid, Cerveja cerveja, int quantidade) {
		TabelaItensVenda tabela = buscaTabelaPorUuid(uuid);
		tabela.adicionarItem(cerveja, quantidade);
		tabelaItens.add(tabela);
	}

	
	public void alterQuantidadeItemCadastrado(String uuid, Cerveja cerveja, Integer quantidade) {
		TabelaItensVenda tabela = buscaTabelaPorUuid(uuid);
		tabela.alterQuantidadeItemCadastrado(cerveja, quantidade);
		
	}

	public void removerCerveja(String uuid, Cerveja cerveja) {
		TabelaItensVenda tabela = buscaTabelaPorUuid(uuid);	
		tabela.removerCerveja(cerveja);
	}

	public List<ItemVenda> getItens(String uuid) {
		return buscaTabelaPorUuid(uuid).getItens();
	}

	private TabelaItensVenda buscaTabelaPorUuid(String uuid) {
		return tabelaItens.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny().orElse(new TabelaItensVenda(uuid));
	}


	public  Object getValorTotal(String uuid) {
		return buscaTabelaPorUuid(uuid).getValorTotal();
	}
}
