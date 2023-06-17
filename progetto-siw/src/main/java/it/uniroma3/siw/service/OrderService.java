package it.uniroma3.siw.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.OrderValidator;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.OrderRepository;
import it.uniroma3.siw.repository.UserRepository;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderValidator orderValidator;

    @Autowired
    protected UserService userService;


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

    

}
