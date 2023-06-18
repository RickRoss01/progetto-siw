package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.model.PriceList;
import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.repository.PriceListItemRepository;
import it.uniroma3.siw.repository.PriceListRepository;



@Component
public class OrderLineValidator implements Validator {
    @Autowired
	private PriceListItemRepository priceListItemRepository;

    @Override
	public void validate(Object o, Errors errors) {
		OrderLine orderLine = (OrderLine)o;
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return OrderLine.class.equals(aClass);
	}

	
}
