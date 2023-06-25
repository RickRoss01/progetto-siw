package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    @Query("SELECT o FROM Invoice o ORDER BY dataFatt ASC")
    List<Invoice> findAllInvoices(Pageable pageable);

    public boolean existsById(Long id); 

    public boolean existsByOrderId(Long id);

}