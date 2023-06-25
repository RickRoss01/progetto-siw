package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.repository.PriceListItemRepository;



@Component
public class PriceListItemValidator implements Validator {
    @Autowired
	private PriceListItemRepository priceListItemRepository;

    @Override
	public void validate(Object o, Errors errors) {
		PriceListItem priceListItem = (PriceListItem)o;
		if (priceListItem.getProduct() != null && priceListItem.getPriceList() != null
				&& priceListItemRepository.existsByProductIdAndPriceListId(priceListItem.getProduct().getId(),priceListItem.getPriceList().getId())) {
			errors.reject("priceListItem.duplicate");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return PriceListItem.class.equals(aClass);
	}

	
}
