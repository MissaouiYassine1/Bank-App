package com.enis.banque.repositories;

import com.enis.banque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> searchByKeyword(@Param("keyword") String keyword);
}