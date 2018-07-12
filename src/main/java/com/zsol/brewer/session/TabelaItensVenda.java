package com.zsol.brewer.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.zsol.brewer.model.Cerveja;
import com.zsol.brewer.model.ItemVenda;


class TabelaItensVenda {
	
	private String Uuid;
	
	private List<ItemVenda> itens = new ArrayList<>();
	
	
	
	public TabelaItensVenda(String uuid) {
		Uuid = uuid;
	}

	public BigDecimal getValorTotal(){
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade){
		
		Optional<ItemVenda> itensVendaOptional = verificarCervejaCadastrada(cerveja);
		
		ItemVenda itemVenda = null;
		
		if(itensVendaOptional.isPresent()){
			itemVenda = itensVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else{			
			itemVenda = new ItemVenda();
			itemVenda.setCerveja(cerveja);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(cerveja.getValor());
			itens.add(0, itemVenda);
		}		
	}
	
	public void alterQuantidadeItemCadastrado(Cerveja cerveja, Integer quantidade){
		ItemVenda itemVenda = verificarCervejaCadastrada(cerveja).get();
		itemVenda.setQuantidade(quantidade);
		
	}
	
	public void removerCerveja(Cerveja cerveja){
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCerveja().equals(cerveja))
				.findAny().getAsInt();
		
		itens.remove(indice);
	}

	private Optional<ItemVenda> verificarCervejaCadastrada(Cerveja cerveja) {
		return itens.stream()
				.filter(i -> i.getCerveja().equals(cerveja))
				.findAny();
	}
	
	public String getUuid() {
		return Uuid;
	}

	public int total(){
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Uuid == null) ? 0 : Uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (Uuid == null) {
			if (other.Uuid != null)
				return false;
		} else if (!Uuid.equals(other.Uuid))
			return false;
		return true;
	}
	
}
