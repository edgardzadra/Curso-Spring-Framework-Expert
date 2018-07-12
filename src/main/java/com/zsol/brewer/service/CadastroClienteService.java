package com.zsol.brewer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsol.brewer.model.Cliente;
import com.zsol.brewer.repository.Clientes;
import com.zsol.brewer.service.exception.CpfCnpjClienteJaCadastradoExpection;

@Service
public class CadastroClienteService {
	
	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar (Cliente cliente){
		Optional<Cliente> clienteCadastrado = clientes.findByCpfOuCnpj(cliente.cpfCnpjSemFormatacao());
		
		if(clienteCadastrado.isPresent()){
			throw new CpfCnpjClienteJaCadastradoExpection("CPF/CNPJ Já Cadastrado");
		}
		
		clientes.save(cliente);			
	}

}
