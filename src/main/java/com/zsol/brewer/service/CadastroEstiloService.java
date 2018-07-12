package com.zsol.brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsol.brewer.model.Estilo;
import com.zsol.brewer.repository.Estilos;
import com.zsol.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private Estilos estilos;

	@Transactional
	public Estilo salvar (Estilo estilo){
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		
		if(estiloOptional.isPresent()){
			throw new NomeEstiloJaCadastradoException("Nome do Estilo Já cadastrado!");
		}
		
		return estilos.saveAndFlush(estilo);
	}
}
