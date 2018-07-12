package com.zsol.brewer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.zsol.brewer.validation.ConfirmacaoAtributo;

public class ValidacaoAtributos implements ConstraintValidator<ConfirmacaoAtributo, Object>{

	private String atributo;
	private String confirmacaoAtributo;
	
	
	@Override
	public void initialize(ConfirmacaoAtributo constraintAnnotation) {
		this.atributo = constraintAnnotation.atributo();
		this.confirmacaoAtributo = constraintAnnotation.confirmacaoAtributo();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valido = false;
		try{
			Object atributo = BeanUtils.getProperty(value, this.atributo);
			Object confirmacaoAtributo = BeanUtils.getProperty(value, this.confirmacaoAtributo);
			
			valido = ambosSaoNull(atributo, confirmacaoAtributo) || ambosSaoIguais(atributo, confirmacaoAtributo);
		} catch (Exception e) {
			throw new RuntimeException("Erro recuperando valor dos atributos ", e);
		}
		
		if(!valido){
			context.disableDefaultConstraintViolation();
			String mensagem = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(mensagem);
			violationBuilder.addPropertyNode(confirmacaoAtributo).addConstraintViolation();
		}
		
		return valido;
	}

	private boolean ambosSaoIguais(Object atributo2, Object confirmacaoAtributo2) {
		return atributo2 != null && atributo2.equals(confirmacaoAtributo2);
	}

	private boolean ambosSaoNull(Object atributo2, Object confirmacaoAtributo2) {
		return atributo2 == null && confirmacaoAtributo2 == null;
	}

}
