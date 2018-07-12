package com.zsol.brewer.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zsol.brewer.model.Cidade;
import com.zsol.brewer.repository.filter.CidadeFilter;

public interface CidadesQueries {
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);
}
