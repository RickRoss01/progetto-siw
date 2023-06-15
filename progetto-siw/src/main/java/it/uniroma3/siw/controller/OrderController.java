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

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.service.OrderService;

@Controller
public class OrderController{


    @Autowired
    OrderService orderService;

    @GetMapping(value = "/orders")
    private String getOrders(Model model){
        model.addAttribute("orders",this.orderService.getAllOrdersBy50(1));
        return "orders.html";
    }

    @GetMapping(value = "/order/{orderId}")
    private String getOrder(@PathVariable("orderId") Long orderId, Model model){
        model.addAttribute("order",this.orderService.getOrder(orderId));
        model.addAttribute("customer", this.orderService.getOrder(orderId).getCustomer());
        return "order.html";
    }

}