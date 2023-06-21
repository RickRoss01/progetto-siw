package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.model.Invoice;
import it.uniroma3.siw.service.InvoiceService;

@Controller
public class InvoiceController{


    @Autowired
    InvoiceService invoiceService;


    @GetMapping(value = "/invoices")
    private String getInvoices(Model model){
        model.addAttribute("invoices",this.invoiceService.getAllInvoicesBy50(1));
        return "invoices.html";
    }

    @GetMapping(value = "/invoice/{invoiceId}")
    private String getOrder(@PathVariable("invoiceId") Long invoiceId, Model model){
        Invoice invoice = this.invoiceService.getInvoice(invoiceId);
        model.addAttribute("invoice",invoice);
        return "invoice.html";
    }

    @GetMapping(value = "/createInvoice/{orderId}")
    private String formNewOrder(@PathVariable("orderId") Long orderId, Model model){
        Invoice invoice = this.invoiceService.newInvoice(orderId);
        model.addAttribute("invoice",invoice);
        
        return "redirect:/invoice/"+invoice.getId();            
    }



}