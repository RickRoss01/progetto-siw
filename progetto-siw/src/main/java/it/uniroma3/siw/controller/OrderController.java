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

import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.service.CustomerService;
import it.uniroma3.siw.service.OrderService;
import it.uniroma3.siw.service.PriceListService;

@Controller
public class OrderController{


    @Autowired
    OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PriceListService priceListService;

    @GetMapping(value = "/orders")
    private String getOrders(Model model){
        model.addAttribute("orders",this.orderService.getAllOrdersBy50(1));
        return "orders.html";
    }

    @GetMapping(value = "/order/{orderId}")
    private String getOrder(@PathVariable("orderId") Long orderId, Model model){
        Order order = this.orderService.getOrder(orderId);
        model.addAttribute("order",order);
        model.addAttribute("customer", this.orderService.getOrder(orderId).getCustomer());
        model.addAttribute("customers", customerService.getAllCustomersNotInOrder(order));
        if(order.getPriceList() == null){
            model.addAttribute("priceLists", priceListService.getAllPriceLists());
        }else
            model.addAttribute("priceLists", priceListService.getAllPriceListsNotInOrder(order));
        return "order.html";
    }

    @GetMapping(value = "/formNewOrder")
    private String formNewOrder(Model model){
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("priceLists", priceListService.getAllPriceLists());
        return "order.html";            
    }

    @PostMapping(value = "/newOrder")
    private String newOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model){
        Order newOrder = this.orderService.newOrder(order, bindingResult);
        model.addAttribute("order", newOrder);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "order.html";
    }

    @PostMapping(value = "/updateOrder")
    private String updateOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model){
        Order newOrder = this.orderService.newOrder(order, bindingResult);
        model.addAttribute("order", newOrder);
        model.addAttribute("customers", customerService.getAllCustomersNotInOrder(order));
        if(order.getPriceList() == null){
            model.addAttribute("priceLists", priceListService.getAllPriceLists());
        }else
            model.addAttribute("priceLists", priceListService.getAllPriceListsNotInOrder(order));
        return "order.html";
    }

    @GetMapping(value = "/deleteOrder/{id}")
    private String deleteOrder(@PathVariable("id") Long orderId,Model model){
        this.orderService.deleteOrder(orderId);
        return getOrders(model);
    }

}