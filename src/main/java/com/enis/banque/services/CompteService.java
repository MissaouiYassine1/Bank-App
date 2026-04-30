package com.enis.banque.services;

import com.enis.banque.dto.CompteDTO;
import com.enis.banque.entities.Compte;

import java.util.List;

public interface CompteService {
    List<Compte> findAll();
    Compte findByRib(String rib);
    Compte save(CompteDTO compteDTO);
    Compte update(String rib, CompteDTO compteDTO);
    void deleteByRib(String rib);
    CompteDTO convertToDTO(Compte compte);
    Compte convertToEntity(CompteDTO compteDTO);
}