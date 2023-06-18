package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.OrderLine;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {


    public boolean existsById(Long id); 

}