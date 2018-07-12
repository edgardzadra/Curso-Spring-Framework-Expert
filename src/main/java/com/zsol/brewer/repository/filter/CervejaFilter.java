package com.zsol.brewer.repository.filter;

import java.math.BigDecimal;

import com.zsol.brewer.model.Estilo;
import com.zsol.brewer.model.Origem;
import com.zsol.brewer.model.Sabor;

public class CervejaFilter {

	private String sku;
	private String nome;
	private Estilo estilo;
	private Sabor sabor;
	private Origem origem;
	private BigDecimal precoDe;
	private BigDecimal precoAte;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public BigDecimal getPrecoDe() {
		return precoDe;
	}
	public void setPrecoDe(BigDecimal precoDe) {
		this.precoDe = precoDe;
	}
	public BigDecimal getPrecoAte() {
		return precoAte;
	}
	public void setPrecoAte(BigDecimal precoAte) {
		this.precoAte = precoAte;
	}
	
	
}
