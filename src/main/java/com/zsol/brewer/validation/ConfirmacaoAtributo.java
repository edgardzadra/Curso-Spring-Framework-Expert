package com.zsol.brewer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.zsol.brewer.validation.validator.ValidacaoAtributos;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidacaoAtributos.class })
public @interface ConfirmacaoAtributo {
	
	@OverridesAttribute(constraint = Pattern.class, name="message")
	String message() default "Atributos nao conferem o valor";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	String atributo();
	
	String confirmacaoAtributo();
}
