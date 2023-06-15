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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Customer;
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
        model.addAttribute("customer",this.customerService.getCustomer(customerId));
        model.addAttribute("orders", this.customerService.getCustomer(customerId).getOrders());
        model.addAttribute("contacts", this.customerService.getCustomer(customerId).getContacts());
        return "customer.html";
    }

    @PostMapping(value = "/formUpdateCustomer")
        private String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model){
            Customer customerUpdate = this.customerService.updateCustomer(customer,bindingResult);
            model.addAttribute("customerUpdate", customerUpdate);
            return "customer.html";
    }
}