package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c ORDER BY createdOn ASC")
    List<Customer> findAllCustomers(Pageable pageable);

    public boolean existsByRagioneSocialeAndIndirizzo(String ragioneSociale, String indirizzo);
}