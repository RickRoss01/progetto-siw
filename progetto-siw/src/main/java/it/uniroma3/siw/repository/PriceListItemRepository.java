package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.PriceListItem;

public interface PriceListItemRepository extends CrudRepository<PriceListItem, Long> {

    @Query(value = "SELECT * " + 
            "FROM price_list_item " + 
            "where id not in (SELECT price_list_item_id from price_list where id = :priceListId) ", nativeQuery=true)
    public List<PriceListItem> getAllPriceListsItemNotInPriceList(@Param("priceListId") Long id);

    @Query(value = "SELECT COUNT(*) FROM price_list " + 
            "WHERE nome = :nome " + 
            "AND id <> :priceListId", nativeQuery=true)
    int countOtherPriceListsWithSameName(@Param("priceListId") Long priceListId,@Param("nome") String customerName);

    @Query(value = "SELECT * FROM price_list_item " + 
            "WHERE pricelist_id = :id ", nativeQuery=true)
    List<PriceListItem> getAllpriceListItemFromPriceList(@Param("id") Long id);

        public boolean existsByProductIdAndPriceListId(Long id, Long id2);

}

