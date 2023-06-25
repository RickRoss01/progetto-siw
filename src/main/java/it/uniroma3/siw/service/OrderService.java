package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.OrderValidator;
import it.uniroma3.siw.model.Customer;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.repository.OrderRepository;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    protected UserService userService;

    @Autowired
    CustomerService customerService;

    public Object getAllOrdersBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.orderRepository.findAllOrders(pageable);
    }

    public Order getOrder(Long id){
        return this.orderRepository.findById(id).get();
    }

    public Order newOrder(@Valid Order order, BindingResult bindingResult) {
        order.setCommerciale(this.userService.getCurrentUser());
        this.orderValidator.validate(order, bindingResult);
        if (bindingResult.hasErrors()) {
            return order;
        }
        this.orderRepository.save(order); 
        return order;
    }

    public Order updateOrder(Order order,BindingResult bindingResult) {
        this.orderValidator.validate(order, bindingResult);
        if (bindingResult.hasErrors()) {
            return order;
        }
        this.orderRepository.save(order); 
        return order;
    }

    public void deleteOrder(Long orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        if(order.isPresent()){
            this.orderRepository.delete(order.get());
        }
    }

    public Order createOrderForCustomer(Long customerId) {
        Order order = new Order();
        order.setCommerciale(this.userService.getCurrentUser());
        Customer customer = this.customerService.getCustomer(customerId);
        order.setCustomer(customer);
        this.orderRepository.save(order); 
        return order;
    }

    public Float getCostoTotaleOrdine(Long orderId) {
        Order order = this.getOrder(orderId);
        List<OrderLine> orderLines = order.getOrderLines();
        Float tot = 0f;
        for(OrderLine orderLine : orderLines){
            tot+=orderLine.getCostoTotale();
        }
        return tot;
    }

    

}