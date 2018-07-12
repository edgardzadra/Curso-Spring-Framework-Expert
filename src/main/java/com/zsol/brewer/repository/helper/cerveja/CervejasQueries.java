package com.zsol.brewer.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zsol.brewer.dto.CervejaDTO;
import com.zsol.brewer.model.Cerveja;
import com.zsol.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {
	
	public Page<Cerveja> filtrar(CervejaFilter filter, Pageable pageable);
	
	public List<CervejaDTO> porSkuOuNome(String skuOuNome);

}
