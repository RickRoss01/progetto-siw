package it.uniroma3.siw.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.OrderLine;



@Component
public class OrderLineValidator implements Validator {
    @Override
	public void validate(Object o, Errors errors) {
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return OrderLine.class.equals(aClass);
	}

	
}
