package com.enis.banque.controllers;


import com.enis.banque.services.ClientService;
import com.enis.banque.services.CompteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ClientService clientService;
    private final CompteService compteService;

    public HomeController(ClientService clientService, CompteService compteService) {
        this.clientService = clientService;
        this.compteService = compteService;
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            long totalClients = clientService.findAll().size();
            long totalComptes = compteService.findAll().size();
            model.addAttribute("totalClients", totalClients);
            model.addAttribute("totalComptes", totalComptes);
        } catch (Exception e) {
            model.addAttribute("totalClients", 0);
            model.addAttribute("totalComptes", 0);
        }
        return "index";
    }
}