package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.OrderRepository;



@Component
public class OrderValidator implements Validator {
    @Autowired
	private OrderRepository orderRepository;

    @Override
	public void validate(Object o, Errors errors) {
		
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Order.class.equals(aClass);
	}

	
}
