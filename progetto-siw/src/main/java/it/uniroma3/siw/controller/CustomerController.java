package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.service.CustomerService;

@Controller
public class CustomerController{


    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers")
    private String getCustomers(Model model){
        model.addAttribute("customers",this.customerService.getAllCustomersBy50(1));
        return "customers.html";
    }

    @GetMapping(value = "/customer/{customerId}")
    private String getCustomer(@PathVariable("customerId") Long customerId, Model model){
        model.addAttribute("customer",this.customerService.getCusomer(customerId));
        return "customer.html";
    }
}