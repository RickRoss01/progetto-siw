package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.CustomerValidator;
import it.uniroma3.siw.model.Contact;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.ContactRepository;
import it.uniroma3.siw.repository.CustomerRepository;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ContactService contactService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired 
	private CustomerValidator customerValidator;

    public Object getAllCustomersBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.customerRepository.findAllCustomers(pageable);
    }

    public Iterable<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        return this.customerRepository.findById(id).get();
    }


    public Customer updateCustomer(Customer customer,BindingResult bindingResult) {
        this.customerValidator.validateExistingCustomer(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return customer;
        }
        this.customerRepository.save(customer); 
        return customer;
    }

    public void deleteCustomer(Long customerId){
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isPresent()) {
            for(Contact contact : customer.get().getContacts()){
                contact.setCustomer(null);
            }
            customerRepository.delete(customer.get());
        }
    }

    public Customer newCustomer(Customer customer, BindingResult bindingResult){
        this.customerValidator.validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            return customer;
        }
        this.customerRepository.save(customer); 
        return customer;
    }

    public List<Customer> getAllCustomersNotInOrder(Order order) {
        return this.customerRepository.getAllCustomersNotInOrder(order.getId());
    }

    
    public void addContactToCustomer(Long contactId, Long customerId) {
        Contact contact = this.contactRepository.findById(contactId).get();
        Customer customer = this.getCustomer(customerId);

        customer.getContacts().add(contact);
        contact.setCustomer(customer);

        this.contactRepository.save(contact);
        this.customerRepository.save(customer);
    }

}
