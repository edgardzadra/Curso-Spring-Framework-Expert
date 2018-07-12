package com.zsol.brewer.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zsol.brewer.model.ItemVenda;
import com.zsol.brewer.model.Venda;
import com.zsol.brewer.repository.Vendas;

@Service
public class CadastroVendaService {

	
	@Autowired
	private Vendas vendas;
	
	@Transactional
	public void salvar(Venda venda){
		if(venda.isNova()){
			venda.setDataCriacao(LocalDateTime.now());
		}
		
		if(venda.getDataEntrega() != null){
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega()
					, venda.getHoraEntrega() != null ? venda.getHoraEntrega() : LocalTime.NOON));
		}
		
		vendas.save(venda);
	}


}
