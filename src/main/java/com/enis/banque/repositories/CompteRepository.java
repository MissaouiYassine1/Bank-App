package com.enis.banque.repositories;

import com.enis.banque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

    List<Compte> findByClientId(Long clientId);
    void deleteByRib(String rib);
}