package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Contact;
import it.uniroma3.siw.service.ContactService;


@Controller
public class ContactController{


    @Autowired
    ContactService contactService;

    @GetMapping(value = "/contacts")
    private String getContacts(Model model){
        model.addAttribute("contacts",this.contactService.getAllContactsBy50(1));
        return "contacts.html";
    }
 
    @GetMapping(value = "/contact/{contactId}")
    private String getContact(@PathVariable("contactId") Long contactId, Model model){
        model.addAttribute("contact",this.contactService.getContact(contactId));
        model.addAttribute("contacts", this.contactService.getContact(contactId).getCustomer());
        return "contact.html";
    }

    @PostMapping(value = "/formUpdateContact")
        private String updateContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model){
            Contact contactUpdate = this.contactService.updateContact(contact,bindingResult);
            model.addAttribute("contactUpdate", contactUpdate);
            return "contact.html";
    }

    @GetMapping(value = "/eliminaContact/{id}")
    private String eliminaContact(@PathVariable("id") Long contactId,Model model){
        this.contactService.deleteContact(contactId);
        return getContacts(model);
    }

    @GetMapping(value = "/formNewContact")
    private String formNewContact(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";            
    }

    @PostMapping(value = "/newContact")
    private String newContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult, Model model){
        Contact newContact = this.contactService.newContact(contact, bindingResult);
        model.addAttribute("contact", newContact);
        return "contact.html";  
    }
    
}