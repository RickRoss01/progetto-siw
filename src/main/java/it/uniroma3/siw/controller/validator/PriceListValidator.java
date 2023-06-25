package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.PriceList;
import it.uniroma3.siw.repository.PriceListRepository;



@Component
public class PriceListValidator implements Validator {
    @Autowired
	private PriceListRepository priceListRepository;

    @Override
	public void validate(Object o, Errors errors) {
		PriceList priceList = (PriceList)o;
		if (priceList.getNome()!=null 
				&& priceListRepository.existsByNome(priceList.getNome())) {
			errors.reject("priceList.duplicate");
		}
	}
	public void validateExistingPriceList(Object o, Errors errors) {
		PriceList priceList = (PriceList)o;
		if (priceList.getNome() != null 
				&& priceListRepository.countOtherPriceListsWithSameName(priceList.getId(), priceList.getNome()) > 0) {
			errors.reject("customer.duplicate");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return PriceList.class.equals(aClass);
	}

	
}
