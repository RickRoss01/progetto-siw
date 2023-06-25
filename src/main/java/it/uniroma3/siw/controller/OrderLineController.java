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
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.service.OrderLineService;
import it.uniroma3.siw.service.OrderService;
import it.uniroma3.siw.service.PriceListItemService;

@Controller
public class OrderLineController{


    @Autowired
    OrderService orderService;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private PriceListItemService priceListItemService;

    @GetMapping(value = "/addOrderLine/{orderId}")
    private String addOrderLine(@PathVariable("orderId") Long orderId,Model model){
        Order order = this.orderService.getOrder(orderId);
        model.addAttribute("order",order);
        model.addAttribute("priceListItem",this.priceListItemService.getAllpriceListItemFromPriceList(order.getPriceList().getId()) );
        model.addAttribute("orderLine", new OrderLine());
        return "order.html";
    }

    @PostMapping(value = "/createOrderLine")
    private String createOrderLine(@Valid @ModelAttribute("OrderLine") OrderLine orderLine, BindingResult bindingResult, Model model){
        OrderLine newOrderLine = this.orderLineService.newOrderLine(orderLine, bindingResult);
        Order order = this.orderService.getOrder(newOrderLine.getOrder().getId());
        model.addAttribute("order",order);
        model.addAttribute("priceListItem",this.priceListItemService.getAllpriceListItemFromPriceList(order.getPriceList().getId()) );
        model.addAttribute("orderLine", new OrderLine());
        return "redirect:/addOrderLine/"+order.getId();
    }

    @GetMapping(value = "/deleteOrderLine/{orderLineId}")
    private String deletePriceListItem(@PathVariable("orderLineId") Long orderLineId , Model model){
        Order order = orderLineService.getOrderLine(orderLineId).getOrder();
        this.orderLineService.deleteOrderLine(orderLineId);
         return "redirect:/order/"+order.getId();
    }


}