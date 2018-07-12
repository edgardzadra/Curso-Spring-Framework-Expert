package com.zsol.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zsol.brewer.model.Cliente;
import com.zsol.brewer.repository.filter.ClienteFilter;

public interface ClientesQueries {
	
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
