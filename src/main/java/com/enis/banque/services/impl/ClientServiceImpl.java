package com.enis.banque.services.impl;

import com.enis.banque.dto.ClientAutoCompleteDTO;
import com.enis.banque.dto.ClientDTO;
import com.enis.banque.entities.Client;
import com.enis.banque.repositories.ClientRepository;
import com.enis.banque.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'ID : " + id));
    }

    @Override
    public Client save(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, ClientDTO clientDTO) {
        Client existingClient = findById(id);
        existingClient.setNom(clientDTO.getNom());
        existingClient.setPrenom(clientDTO.getPrenom());
        return clientRepository.save(existingClient);
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client non trouvé avec l'ID : " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientAutoCompleteDTO> searchClients(String keyword) {
        List<Client> clients = clientRepository.searchByKeyword(keyword);
        return clients.stream()
                .map(client -> new ClientAutoCompleteDTO(
                        client.getId(),
                        client.getNom() + " " + client.getPrenom(),
                        client.getNom() + " " + client.getPrenom()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO convertToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        return dto;
    }

    @Override
    public Client convertToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        return client;
    }
}