package com.enis.banque.services.impl;

import com.enis.banque.dto.CompteDTO;
import com.enis.banque.entities.Client;
import com.enis.banque.entities.Compte;
import com.enis.banque.repositories.CompteRepository;
import com.enis.banque.services.ClientService;
import com.enis.banque.services.CompteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;
    private final ClientService clientService;

    public CompteServiceImpl(CompteRepository compteRepository, ClientService clientService) {
        this.compteRepository = compteRepository;
        this.clientService = clientService;
    }

    @Override
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    @Override
    public Compte findByRib(String rib) {
        return compteRepository.findById(rib)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec le RIB : " + rib));
    }

    @Override
    public Compte save(CompteDTO compteDTO) {
        Compte compte = convertToEntity(compteDTO);
        return compteRepository.save(compte);
    }

    @Override
    public Compte update(String rib, CompteDTO compteDTO) {
        Compte existingCompte = findByRib(rib);
        existingCompte.setSolde(compteDTO.getSolde());

        if (compteDTO.getClientId() != null) {
            Client client = clientService.findById(compteDTO.getClientId());
            existingCompte.setClient(client);
        }

        return compteRepository.save(existingCompte);
    }

    @Override
    public void deleteByRib(String rib) {
        if (!compteRepository.existsById(rib)) {
            throw new RuntimeException("Compte non trouvé avec le RIB : " + rib);
        }
        compteRepository.deleteById(rib);
    }

    @Override
    public CompteDTO convertToDTO(Compte compte) {
        CompteDTO dto = new CompteDTO();
        dto.setRib(compte.getRib());
        dto.setSolde(compte.getSolde());
        if (compte.getClient() != null) {
            dto.setClientId(compte.getClient().getId());
            dto.setClientNom(compte.getClient().getNom());
            dto.setClientPrenom(compte.getClient().getPrenom());
        }
        return dto;
    }

    @Override
    public Compte convertToEntity(CompteDTO compteDTO) {
        Compte compte = new Compte();
        compte.setRib(compteDTO.getRib());
        compte.setSolde(compteDTO.getSolde());

        if (compteDTO.getClientId() != null) {
            Client client = clientService.findById(compteDTO.getClientId());
            compte.setClient(client);
        }

        return compte;
    }
}