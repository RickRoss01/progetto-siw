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
import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.service.OrderService;
import it.uniroma3.siw.service.PriceListItemService;
import it.uniroma3.siw.service.PriceListService;
import it.uniroma3.siw.service.ProductService;

@Controller
public class PriceListController{


    @Autowired
    OrderService orderService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private PriceListItemService priceListItemService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/priceLists")
    private String getPriceLists(Model model){
        model.addAttribute("priceLists",this.priceListService.getAllPriceListsBy50(1));
        return "priceLists.html";
    }

    @GetMapping(value = "/priceList/{priceListId}")
    private String getPriceList(@PathVariable("priceListId") Long priceListId, Model model){
        PriceList priceList = this.priceListService.getPriceList(priceListId);
        model.addAttribute("priceList",priceList);
        
        return "priceList.html";
    }

    @GetMapping(value = "/formNewPriceList")
    private String formNewPriceList(Model model){
        model.addAttribute("priceList", new PriceList());
        return "priceList.html";            
    }

    @PostMapping(value = "/newPriceList")
    private String newPriceList(@Valid @ModelAttribute("priceList") PriceList priceList, BindingResult bindingResult, Model model){
        PriceList newPriceList = this.priceListService.newPriceList(priceList, bindingResult);
        model.addAttribute("priceList", newPriceList);
        return "priceList.html";
    }

    @PostMapping(value = "/updatePriceList")
    private String updatePriceList(@Valid @ModelAttribute("priceList") PriceList priceList, BindingResult bindingResult, Model model){
        PriceList newPriceList = this.priceListService.updatePriceList(priceList, bindingResult);
        model.addAttribute("order", newPriceList);
        return "priceList.html";
    }

    @GetMapping(value = "/deletePriceList/{id}")
    private String deletePriceList(@PathVariable("id") Long priceListId,Model model){
        this.priceListService.deletePriceList(priceListId);
        return getPriceLists(model);
    }


    @GetMapping(value = "/addProductToPriceList/{priceListId}")
    private String addProductToPriceList(@PathVariable("priceListId") Long priceListId, Model model){
        PriceList priceList = this.priceListService.getPriceList(priceListId);
        model.addAttribute("priceList",priceList);
        model.addAttribute("productsToAdd",this.productService.getAllProducts());
        model.addAttribute("priceListItem", new PriceListItem());
        return "priceList.html";
    }

    @PostMapping(value = "/createPriceListItem")
    private String createPriceListItem(@Valid @ModelAttribute("priceListItem") PriceListItem priceListItem, BindingResult bindingResult, Model model){
        PriceListItem newPriceListItem = this.priceListItemService.newPriceListItem(priceListItem, bindingResult);
        PriceList priceList = this.priceListService.getPriceList(newPriceListItem.getPriceList().getId());
        model.addAttribute("priceList",priceList);
        model.addAttribute("productsToAdd",this.productService.getAllProducts() );
        model.addAttribute("priceListItem", newPriceListItem);

        return "priceList.html";
    }

    @GetMapping(value = "/deletePriceListItem/{priceListItemId}")
    private String deletePriceListItem(@PathVariable("priceListItemId") Long priceListItemId , Model model){
        PriceList priceList = priceListItemService.getPriceListItem(priceListItemId).getPriceList();
        this.priceListItemService.deletePriceListItem(priceListItemId);
        return getPriceList(priceList.getId(), model);
    }


}