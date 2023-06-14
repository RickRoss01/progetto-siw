package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.repository.CustomerRepository;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Object getAllCustomersBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.customerRepository.findAllCustomers(pageable);
    }

    public Customer getCusomer(Long id){
        return this.customerRepository.findById(id).get();
    }

    public Customer updateCustomer(Customer customer) {
        Customer c = this.customerRepository.findById(customer.getId()).get(); 
        this.customerRepository.save(customer); 
        return c;
    }


}
