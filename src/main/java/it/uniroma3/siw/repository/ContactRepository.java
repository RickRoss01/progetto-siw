package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c")
    List<Contact> findAllContacts(Pageable pageable);

    public boolean existsByCognomeAndEmail(String cognome, String email);


    public int countByCognomeAndEmail(String cognome, String email);

    @Query(value = "SELECT COUNT(*) FROM contact " + 
            "WHERE cognome = :cognome AND email = :email " + 
            "AND id <> :contactId", nativeQuery=true)
    int countOtherCustomersWithSameCognomeAndEmail(@Param("contactId") Long id,@Param("cognome") String cognome, @Param("email") String email);

    @Query(value = "SELECT * FROM contact " +
                    "WHERE customer_id isnull", nativeQuery = true)
    Iterable<Contact> getAllAvailableContacts();
}


