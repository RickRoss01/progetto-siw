package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.Validate;

import it.uniroma3.siw.controller.validator.CustomerValidator;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.repository.CustomerRepository;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired 
	private CustomerValidator customerValidator;

    public Object getAllCustomersBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.customerRepository.findAllCustomers(pageable);
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


}
