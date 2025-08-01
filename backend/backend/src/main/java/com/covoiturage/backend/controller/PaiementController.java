package com.covoiturage.backend.controller;

import com.covoiturage.backend.model.Paiement;
import com.covoiturage.backend.service.PaiementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    // 🔹 Ajouter un paiement
    @PostMapping
    public Paiement createPaiement(@RequestBody Paiement paiement) {
        return paiementService.createPaiement(paiement);
    }

    // 🔹 Récupérer tous les paiements
    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    // 🔹 Récupérer un paiement par ID
    @GetMapping("/{id}")
    public Optional<Paiement> getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    // 🔹 Récupérer les paiements d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Paiement> getPaiementsByUtilisateur(@PathVariable Long utilisateurId) {
        return paiementService.getPaiementsByUtilisateur(utilisateurId);
    }

    // 🔹 Récupérer les paiements d'une demande
    @GetMapping("/demande/{demandeId}")
    public List<Paiement> getPaiementsByDemande(@PathVariable Long demandeId) {
        return paiementService.getPaiementsByDemande(demandeId);
    }
}
