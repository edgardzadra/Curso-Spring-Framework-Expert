package com.zsol.brewer.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Length(message="Tamanho invalido para logradouro", max=50)
	private String logradouro;
	
	@Length(message="Tamanho invalido para número", max=15)
	private String numero;
	
	@Length(message="Tamanho invalido para complemento", max=20)
	private String complemento;
	private String cep;
	
	@Transient
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="codigo_cidade")
	private Cidade cidade;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNomeCidadeSiglaEstado(){
		if(this.cidade != null){
			return this.cidade.getNome() + "/" + this.cidade.getEstado().getSigla();
		}
		
		return null;
	}
}
