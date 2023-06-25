package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.PriceListItemValidator;
import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.repository.OrderLineRepository;
import it.uniroma3.siw.repository.PriceListItemRepository;


@Service
public class PriceListItemService {

    @Autowired
    PriceListItemRepository priceListItemRepository;

    @Autowired
    PriceListItemValidator priceListItemValidator;

    @Autowired
    OrderLineRepository orderLineRepository;




    public PriceListItem newPriceListItem(@Valid PriceListItem priceListItem, BindingResult bindingResult) {
        this.priceListItemValidator.validate(priceListItem, bindingResult);
        if (bindingResult.hasErrors()) {
            return priceListItem;
        }
        this.priceListItemRepository.save(priceListItem); 
        return priceListItem;
    }

    public PriceListItem getPriceListItem(Long id){
        return this.priceListItemRepository.findById(id).get();
    }


    public void deletePriceListItem(Long priceListItemId) {
        Optional<PriceListItem> priceListItem = this.priceListItemRepository.findById(priceListItemId);
        if(priceListItem.isPresent()){
            this.priceListItemRepository.delete(priceListItem.get());
        }
    }

    public List<PriceListItem> getAllpriceListItemFromPriceList(Long id) {
        return this.priceListItemRepository.getAllpriceListItemFromPriceList(id);
    }

    

}
