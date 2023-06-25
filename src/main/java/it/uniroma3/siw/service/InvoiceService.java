package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Invoice;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.InvoiceRepository;
import it.uniroma3.siw.repository.OrderRepository;


@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    public Object getAllInvoicesBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.invoiceRepository.findAllInvoices(pageable);
    }

    public Invoice getInvoice(Long id){
        return this.invoiceRepository.findById(id).get();
    }

    public Invoice newInvoice(Long orderId) {
        Invoice invoice = new Invoice();
        Order order = this.orderService.getOrder(orderId);
        order.setInvoice(invoice);
        invoice.setOrder(order);
        invoice.setCostoTot(this.orderService.getCostoTotaleOrdine(orderId));
        if(this.invoiceRepository.existsByOrderId(orderId)){
            return null;
        }
        this.invoiceRepository.save(invoice);
        this.orderRepository.save(order);
        
        return invoice;
    }


    public void deleteInvoice(Long invoiceId) {
        Optional<Invoice> invoice = this.invoiceRepository.findById(invoiceId);
        if(invoice.isPresent()){
            this.invoiceRepository.delete(invoice.get());
        }
    }



    

}
