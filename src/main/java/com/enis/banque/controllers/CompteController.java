package com.enis.banque.controllers;

import com.enis.banque.dto.CompteDTO;
import com.enis.banque.entities.Client;
import com.enis.banque.entities.Compte;
import com.enis.banque.repositories.ClientRepository;
import com.enis.banque.repositories.CompteRepository;
import com.enis.banque.services.CompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    // ============ LISTE ============
    @GetMapping
    public String listComptes(Model model) {
        List<Compte> comptes = compteService.findAll();
        model.addAttribute("comptes", comptes);
        return "comptes/list";
    }

    // ============ AJOUT - Formulaire ============
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("compteDTO", new CompteDTO());
        model.addAttribute("isEdit", false);
        return "comptes/form";
    }

    // ============ AJOUT - Sauvegarde ============
    @PostMapping("/save")
    public String saveCompte(@Valid @ModelAttribute("compteDTO") CompteDTO compteDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "comptes/form";
        }
        compteService.save(compteDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Compte ajouté avec succès !");
        return "redirect:/comptes";
    }

    // ============ MODIFICATION - Formulaire ============
    @GetMapping("/edit/{rib}")
    public String showEditForm(@PathVariable String rib, Model model) {
        Compte compte = compteService.findByRib(rib);
        CompteDTO compteDTO = compteService.convertToDTO(compte);
        model.addAttribute("compteDTO", compteDTO);
        model.addAttribute("isEdit", true);
        return "comptes/form";
    }

    // ============ MODIFICATION - Sauvegarde ============
    @PostMapping("/update/{rib}")
    public String updateCompte(@PathVariable String rib,
                               @Valid @ModelAttribute("compteDTO") CompteDTO compteDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "comptes/form";
        }
        compteService.update(rib, compteDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Compte modifié avec succès !");
        return "redirect:/comptes";
    }

    // ============ SUPPRESSION AJAX ============
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<?> deleteCompte(@RequestParam String rib) {
        try {
            compteService.deleteByRib(rib);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}