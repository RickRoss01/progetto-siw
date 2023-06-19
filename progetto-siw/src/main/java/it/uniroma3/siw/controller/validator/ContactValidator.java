package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Contact;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.repository.ContactRepository;



@Component
public class ContactValidator implements Validator {
    @Autowired
	private ContactRepository contactRepository;

    @Override
	public void validate(Object o, Errors errors) {
		Contact contact = (Contact)o;
		if (contact.getCognome()!=null && contact.getEmail()!=null
				&& contactRepository.existsByCognomeAndEmail(contact.getCognome(),contact.getEmail())) {
			errors.reject("contact.duplicate");
		}
	}
	
	public void validateExistingContact(Object o, Errors errors) {
		Contact contact = (Contact)o;
		Integer s = contactRepository.countByCognomeAndEmail(contact.getCognome(),contact.getEmail());
		if (contact.getCognome()!=null && contact.getEmail()!=null
				&& contactRepository.countOtherCustomersWithSameCognomeAndEmail(contact.getId(), contact.getCognome(),contact.getEmail()) > 0) {
			errors.reject("customer.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Customer.class.equals(aClass);
	}
}
