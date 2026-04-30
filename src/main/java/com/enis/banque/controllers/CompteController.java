package com.enis.banque.controllers;

import com.enis.banque.entities.Client;
import com.enis.banque.entities.Compte;
import com.enis.banque.repositories.ClientRepository;
import com.enis.banque.repositories.CompteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comptes")
public class CompteController {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;

    public CompteController(CompteRepository compteRepository, ClientRepository clientRepository) {
        this.compteRepository = compteRepository;
        this.clientRepository = clientRepository;
    }

    // LISTE
    @GetMapping
    public String list(Model model) {
        model.addAttribute("comptes", compteRepository.findAll());
        return "comptes/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("compte", new Compte());
        return "comptes/form";
    }

    // SAUVEGARDE
    @PostMapping("/save")
    public String save(@ModelAttribute Compte compte, @RequestParam Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        compte.setClient(client);
        compteRepository.save(compte);
        return "redirect:/comptes";
    }

    // FORMULAIRE MODIFICATION
    @GetMapping("/edit/{rib}")
    public String editForm(@PathVariable String rib, Model model) {
        Compte compte = compteRepository.findById(rib).orElseThrow();
        model.addAttribute("compte", compte);
        model.addAttribute("clientNom", compte.getClient().getNom() + " " + compte.getClient().getPrenom());
        return "comptes/form";
    }

    // SUPPRESSION AJAX
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam String rib) {
        try {
            compteRepository.deleteById(rib);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}