package com.enis.banque.controllers;
import com.enis.banque.dto.ClientAutoCompleteDTO;
import com.enis.banque.dto.ClientDTO;
import com.enis.banque.entities.Client;
import com.enis.banque.repositories.ClientRepository;
import com.enis.banque.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // ============ LISTE ============
    @GetMapping
    public String listClients(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clients/list";
    }

    // ============ AJOUT - Formulaire ============
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        model.addAttribute("isEdit", false);
        return "clients/form";
    }

    // ============ AJOUT - Sauvegarde ============
    @PostMapping("/save")
    public String saveClient(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "clients/form";
        }
        clientService.save(clientDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Client ajouté avec succès !");
        return "redirect:/clients";
    }

    // ============ MODIFICATION - Formulaire ============
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id);
        ClientDTO clientDTO = clientService.convertToDTO(client);
        model.addAttribute("clientDTO", clientDTO);
        model.addAttribute("isEdit", true);
        return "clients/form";
    }

    // ============ MODIFICATION - Sauvegarde ============
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id,
                               @Valid @ModelAttribute("clientDTO") ClientDTO clientDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "clients/form";
        }
        clientService.update(id, clientDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Client modifié avec succès !");
        return "redirect:/clients";
    }

    // ============ SUPPRESSION AJAX ============
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteClient(@RequestParam Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // ============ AUTO-COMPLÉTION ============
    @GetMapping("/autocomplete")
    @ResponseBody
    public List<ClientAutoCompleteDTO> autocomplete(@RequestParam String term) {
        return clientService.searchClients(term);
    }
}