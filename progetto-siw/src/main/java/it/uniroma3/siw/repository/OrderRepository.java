package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT o FROM Order o ORDER BY createdOn ASC")
    List<Order> findAllOrders(Pageable pageable);

}