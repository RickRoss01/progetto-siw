package it.uniroma3.siw.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.ContactValidator;
import it.uniroma3.siw.model.Contact;
import it.uniroma3.siw.repository.ContactRepository;


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
    
    public Iterable<Contact> getAllAvailaContacts(){
        return this.contactRepository.getAllAvailableContacts();
    }

    public Contact getContact(Long id){
        return this.contactRepository.findById(id).get();
    }
    
    public void save(Contact contact){
        this.contactRepository.save(contact);
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

    public Contact findById(Long contactId) {
        return this.contactRepository.findById(contactId).get();
    }

    @Transactional
    public void removeCustomer(Long contactId) {
        Optional<Contact> contact =  this.contactRepository.findById(contactId);
        if(contact.isPresent())
            contact.get().setCustomer(null);
    }

}
