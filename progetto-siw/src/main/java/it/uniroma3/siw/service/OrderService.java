package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.repository.OrderRepository;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


    public Object getAllOrdersBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.orderRepository.findAllOrders(pageable);
    }

    public Order getOrder(Long id){
        return this.orderRepository.findById(id).get();
    }

}
