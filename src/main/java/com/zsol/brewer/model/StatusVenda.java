package com.zsol.brewer.model;

public enum StatusVenda {

	ORCAMENTO("orcamento"),
	EMITIDA("Emitida"),
	CANCELADA("Cancelada");
	
	private String descricao;
	
	StatusVenda(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
