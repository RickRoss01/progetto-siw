package it.uniroma3.siw.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.OrderLineValidator;
import it.uniroma3.siw.model.OrderLine;
import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.repository.OrderLineRepository;
import it.uniroma3.siw.repository.PriceListItemRepository;


@Service
public class OrderLineService {

    @Autowired
    PriceListItemRepository priceListItemRepository;

    @Autowired
    OrderLineValidator orderLineValidator;

    @Autowired
    OrderLineRepository orderLineRepository;




    public OrderLine newOrderLine(@Valid OrderLine orderLine, BindingResult bindingResult) {
        this.orderLineValidator.validate(orderLine, bindingResult);
        if (bindingResult.hasErrors()) {
            return orderLine;
        }
        Float costoTotale = (float) (orderLine.getPriceListItem().getCosto()*orderLine.getQuantita());
        orderLine.setCostoTotale(costoTotale);
        this.orderLineRepository.save(orderLine); 
        return orderLine;
    }

    public OrderLine getOrderLine(Long orderLineId){
        return this.orderLineRepository.findById(orderLineId).get();
    }



    public void deletePriceListItem(Long priceListItemId) {
        Optional<PriceListItem> priceListItem = this.priceListItemRepository.findById(priceListItemId);
        if(priceListItem.isPresent()){
            this.priceListItemRepository.delete(priceListItem.get());
        }
    }

    public void deleteOrderLine(Long orderLineId) {
        Optional<OrderLine> orderline = this.orderLineRepository.findById(orderLineId);
        if(orderline.isPresent()){
            this.orderLineRepository.delete(orderline.get());
        }

    }


    

}
