package com.enis.banque.services;

import com.enis.banque.dto.ClientAutoCompleteDTO;
import com.enis.banque.dto.ClientDTO;
import com.enis.banque.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> findAll();
    Client findById(Long id);
    Client save(ClientDTO clientDTO);
    Client update(Long id, ClientDTO clientDTO);
    void deleteById(Long id);
    List<ClientAutoCompleteDTO> searchClients(String keyword);
    ClientDTO convertToDTO(Client client);
    Client convertToEntity(ClientDTO clientDTO);
}