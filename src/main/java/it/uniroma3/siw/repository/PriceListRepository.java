package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.PriceList;

public interface PriceListRepository extends CrudRepository<PriceList, Long> {
    @Query("SELECT p FROM PriceList p ORDER BY p.createdOn ASC")
    List<PriceList> findAllOrders(Pageable pageable);

    public boolean existsById(Long id); 

    @Query(value = "SELECT * " + 
            "FROM price_list " + 
            "where id not in (SELECT price_list_id from orders where id = :orderId) ", nativeQuery=true)
    public List<PriceList> getAllPriceListsNotInOrder(@Param("orderId") Long id);

    @Query(value = "SELECT COUNT(*) FROM price_list " + 
            "WHERE nome = :nome " + 
            "AND id <> :priceListId", nativeQuery=true)
    int countOtherPriceListsWithSameName(@Param("priceListId") Long priceListId,@Param("nome") String customerName);

    boolean existsByNome(String nome);
}

