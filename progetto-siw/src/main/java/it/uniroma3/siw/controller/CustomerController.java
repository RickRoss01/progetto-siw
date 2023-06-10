package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.service.CustomerService;

@Controller
public class CustomerController{


    @Autowired
    CustomerService customerService;

    @GetMapping(value = "/customers")
    private String getCustomers(Model model){
        model.addAttribute("customers",this.customerService.getAllCustomersBy50());
        return "customers.html";
    }
}