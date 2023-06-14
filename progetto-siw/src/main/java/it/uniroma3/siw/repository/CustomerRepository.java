package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c ORDER BY createdOn ASC")
    List<Customer> findAllCustomers(Pageable pageable);

    public boolean existsByragioneSocialeAndIndirizzo(String ragioneSociale, String indirizzo);

    public boolean existsBypIva(String pIva);

    public int countBypIva(String getpIva);

    @Query(value = "SELECT COUNT(*) FROM customer " + 
            "WHERE p_iva = :pIva " + 
            "AND id <> :customerId", nativeQuery=true)
    int countOtherCustomersWithSamePIva(@Param("customerId") Long id,@Param("pIva") String getpIva);
}