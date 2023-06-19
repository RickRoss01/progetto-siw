package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.ContactValidator;
import it.uniroma3.siw.controller.validator.CustomerValidator;
import it.uniroma3.siw.model.Contact;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.ContactRepository;
import it.uniroma3.siw.repository.CustomerRepository;


@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired 
	private ContactValidator contactValidator;

    public Object getAllContactsBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.contactRepository.findAllContacts(pageable);
    }

    public Iterable<Contact> getAllContacts(){
        return this.contactRepository.findAll();
    }

    public Contact getContact(Long id){
        return this.contactRepository.findById(id).get();
    }


    public Contact updateContact(Contact contact,BindingResult bindingResult) {
        this.contactValidator.validateExistingContact(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            return contact;
        }
        this.contactRepository.save(contact); 
        return contact;
    }

    public void deleteContact(Long contactId){
        Optional<Contact> contact = this.contactRepository.findById(contactId);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
        }
    }

    public Contact newContact(Contact contact, BindingResult bindingResult){
        this.contactValidator.validate(contact, bindingResult);
        if (bindingResult.hasErrors()) {
            return contact;
        }
        this.contactRepository.save(contact); 
        return contact;
    }


}
