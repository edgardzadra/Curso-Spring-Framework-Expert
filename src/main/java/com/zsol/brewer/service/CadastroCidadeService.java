package com.zsol.brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsol.brewer.model.Cidade;
import com.zsol.brewer.repository.Cidades;
import com.zsol.brewer.service.exception.CidadeJaCadastradaException;

@Service
public class CadastroCidadeService {

	@Autowired
	private Cidades cidades;
	
	
	@Transactional
	public void salvar(Cidade cidade){
		Optional<Cidade> cidadeCadastrada = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if(cidadeCadastrada.isPresent()){
			throw new CidadeJaCadastradaException("A cidade: " + cidade.getNome() +" já está cadastrada para este Estado");
		}
		
		cidades.save(cidade);
	}
	
}
