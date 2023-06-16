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

import it.uniroma3.siw.model.PriceList;
import it.uniroma3.siw.service.OrderService;
import it.uniroma3.siw.service.PriceListService;

@Controller
public class PriceListController{


    @Autowired
    OrderService orderService;

    @Autowired
    private PriceListService priceListService;

    @GetMapping(value = "/priceLists")
    private String getPriceLists(Model model){
        model.addAttribute("priceLists",this.priceListService.getAllPriceListsBy50(1));
        return "priceLists.html";
    }

    @GetMapping(value = "/priceList/{priceListId}")
    private String getOrder(@PathVariable("priceListId") Long priceListId, Model model){
        PriceList priceList = this.priceListService.getPriceList(priceListId);
        model.addAttribute("priceList",priceList);
        
        return "priceList.html";
    }

    @GetMapping(value = "/formNewPriceList")
    private String formNewOrder(Model model){
        model.addAttribute("priceList", new PriceList());
        return "priceList.html";            
    }

    @PostMapping(value = "/newPriceList")
    private String newOrder(@Valid @ModelAttribute("priceList") PriceList priceList, BindingResult bindingResult, Model model){
        PriceList newPriceList = this.priceListService.newPriceList(priceList, bindingResult);
        model.addAttribute("priceList", newPriceList);
        return "priceList.html";
    }

    @PostMapping(value = "/updatePriceList")
    private String updateOrder(@Valid @ModelAttribute("priceList") PriceList priceList, BindingResult bindingResult, Model model){
        PriceList newPriceList = this.priceListService.updatePriceList(priceList, bindingResult);
        model.addAttribute("order", newPriceList);
        return "priceList.html";
    }

    @GetMapping(value = "/deletePriceList/{id}")
    private String deleteOrder(@PathVariable("id") Long priceListId,Model model){
        this.priceListService.deletePriceList(priceListId);
        return getPriceLists(model);
    }

}