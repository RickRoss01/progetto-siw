package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.repository.ProductRepository;



@Component
public class ProductValidator implements Validator {
    @Autowired
	private ProductRepository productRepository;

    @Override
	public void validate(Object o, Errors errors) {
		Product product = (Product)o;
		if (product.getNome() != null 
				&& productRepository.existsByNome(product.getNome())) {
			errors.reject("product.duplicate");
		}
	}
	
	public void validateExistingProduct(Object o, Errors errors) {
		Product product = (Product)o;
		if (product.getNome() != null 
				&& productRepository.countOtherProductWithSameName(product.getId(), product.getNome()) > 0) {
			errors.reject("product.duplicate");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Order.class.equals(aClass);
	}

	

	
}
