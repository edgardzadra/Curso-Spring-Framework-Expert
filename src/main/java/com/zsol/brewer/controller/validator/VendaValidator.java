package com.zsol.brewer.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zsol.brewer.model.Venda;

@Component
public class VendaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Venda.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "cliente.codigo", "", "Selecione um cliente na pesquisa rapida");
		Venda venda = (Venda) target;
		validacaoDataHorario(errors, venda);
		validacaoItensNaTabela(errors, venda);
		validacaoValorNegativo(errors, venda);
	}

	private void validacaoValorNegativo(Errors errors, Venda venda) {
		if(venda.getValorTotal().compareTo(BigDecimal.ZERO) < 0){
			errors.reject("", "valor total não pode ser negativo");
		}
	}

	private void validacaoItensNaTabela(Errors errors, Venda venda) {
		if(venda.getItens().isEmpty()){
			errors.reject("", "Adicione pelo menos um item na venda");
		}
	}

	private void validacaoDataHorario(Errors errors, Venda venda) {
		if(venda.getHoraEntrega() != null && venda.getDataEntrega() == null){
			errors.rejectValue("dataEntrega", "", "Informe uma data de entrega para o horario informado");
		}
	}

	
}
