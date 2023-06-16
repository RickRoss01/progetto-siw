package it.uniroma3.siw.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.controller.validator.ProductValidator;
import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.repository.ProductRepository;


@Service
public class ProductService {

    @Autowired 
    private ProductRepository productRepository;

    @Autowired
    private ProductValidator productValidator;

    public Product getProduct(Long id){
        return this.productRepository.findById(id).get();
    }
    
    public Product newProduct(@Valid Product product, BindingResult bindingResult) {
        this.productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return product;
        }
        this.productRepository.save(product); 
        return product;
    }
    
    public Product updateOrder(Product product,BindingResult bindingResult) {
        this.productValidator.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return product;
        }
        this.productRepository.save(product); 
        return product;
    }

    
    public void deleteProduct(Long productId) {
        Optional<Product> product = this.productRepository.findById(productId);
        if(product.isPresent()){
            this.productRepository.delete(product.get());
        }
    }

    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }    

}
