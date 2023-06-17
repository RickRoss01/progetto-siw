package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    
    public boolean existsById(Long id); 

}