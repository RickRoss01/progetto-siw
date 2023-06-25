package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.repository.CustomerRepository;



@Component
public class CustomerValidator implements Validator {
    @Autowired
	private CustomerRepository customerRepository;

    @Override
	public void validate(Object o, Errors errors) {
		Customer customer = (Customer)o;
		if (customer.getpIva()!=null 
				&& customerRepository.existsBypIva(customer.getpIva())) {
			errors.reject("customer.duplicate");
		}
	}
	public void validateExistingCustomer(Object o, Errors errors) {
		Customer customer = (Customer)o;
		if (customer.getpIva()!=null 
				&& customerRepository.countOtherCustomersWithSamePIva(customer.getId(), customer.getpIva()) > 0) {
			errors.reject("customer.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Customer.class.equals(aClass);
	}
}
