package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.PriceListItemValidator;
import it.uniroma3.siw.controller.validator.PriceListValidator;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.PriceList;
import it.uniroma3.siw.model.PriceListItem;
import it.uniroma3.siw.repository.PriceListItemRepository;
import it.uniroma3.siw.repository.PriceListRepository;


@Service
public class PriceListItemService {

    @Autowired
    PriceListItemRepository priceListItemRepository;

    @Autowired
    PriceListItemValidator priceListItemValidator;




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

    

}
