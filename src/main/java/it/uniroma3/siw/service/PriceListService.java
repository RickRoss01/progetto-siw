package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.PriceListValidator;
import it.uniroma3.siw.model.Order;
import it.uniroma3.siw.model.PriceList;
import it.uniroma3.siw.repository.PriceListRepository;


@Service
public class PriceListService {

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    PriceListValidator priceListValidator;



    public Object getAllPriceListsBy50(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 6);
        return this.priceListRepository.findAllOrders(pageable);
    }

    public PriceList getPriceList(Long id){
        return this.priceListRepository.findById(id).get();
    }

    public PriceList newPriceList(@Valid PriceList priceList, BindingResult bindingResult) {
        this.priceListValidator.validate(priceList, bindingResult);
        if (bindingResult.hasErrors()) {
            return priceList;
        }
        this.priceListRepository.save(priceList); 
        return priceList;
    }

    public PriceList updatePriceList(PriceList priceList,BindingResult bindingResult) {
        this.priceListValidator.validateExistingPriceList(priceList, bindingResult);
        if (bindingResult.hasErrors()) {
            return priceList;
        }
        this.priceListRepository.save(priceList); 
        return priceList;
    }

    public void deletePriceList(Long orderId) {
        Optional<PriceList> priceList = this.priceListRepository.findById(orderId);
        if(priceList.isPresent()){
            this.priceListRepository.delete(priceList.get());
        }
    }

    public List<PriceList> getAllPriceListsNotInOrder(Order order) {
        return this.priceListRepository.getAllPriceListsNotInOrder(order.getId());
    }

    public List<PriceList> getAllPriceLists() {
        return (List<PriceList>) this.priceListRepository.findAll();
    }

}