package com.enis.banque.controllers;
import com.enis.banque.entities.Client;
import com.enis.banque.repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // LISTE
    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new Client());
        return "clients/form";
    }

    // SAUVEGARDE
    @PostMapping("/save")
    public String save(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }

    // FORMULAIRE MODIFICATION
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        model.addAttribute("client", client);
        return "clients/form";
    }

    // SUPPRESSION AJAX
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // AUTO-COMPLÉTION (JSON)
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<Map<String, Object>> autocomplete(@RequestParam String term) {
        List<Client> clients = clientRepository.search(term);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Client c : clients) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("label", c.getNom() + " " + c.getPrenom());
            map.put("value", c.getNom() + " " + c.getPrenom());
            result.add(map);
        }
        return result;
    }
}