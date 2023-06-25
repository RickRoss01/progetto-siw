package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    
    public boolean existsByNome(String nome); 

    @Query(value = "SELECT COUNT(*) FROM product " + 
            "WHERE nome = :nome " + 
            "AND id <> :productId", nativeQuery=true)
    int countOtherProductWithSameName(@Param("productId") Long id,@Param("nome") String nome);

}