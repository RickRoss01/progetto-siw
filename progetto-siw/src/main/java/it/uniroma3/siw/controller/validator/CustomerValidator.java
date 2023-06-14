package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.repository.CustomerRepository;

import org.hibernate.validator.cfg.context.ConstraintDefinitionContext.ValidationCallable;



@Component
public class CustomerValidator implements Validator {
    @Autowired
	private CustomerRepository customerRepository;

    @Override
	public void validate(Object o, Errors errors) {
		Customer customer = (Customer)o;
		if (customer.getRagioneSociale()!=null && customer.getIndirizzo()!=null 
				&& customerRepository.existsByRagioneSocialeAndIndirizzo(customer.getRagioneSociale(),customer.getIndirizzo())) {
			errors.reject("customer.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Customer.class.equals(aClass);
	}
}
