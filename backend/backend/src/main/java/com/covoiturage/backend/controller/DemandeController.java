package com.covoiturage.backend.controller;

import com.covoiturage.backend.model.Demande;
import com.covoiturage.backend.service.DemandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
@CrossOrigin(origins = "*")
public class DemandeController {

    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping
    public ResponseEntity<Demande> creerDemande(@RequestParam Long passagerId,
                                                @RequestParam Long trajetId) {
        Demande demande = demandeService.creerDemande(passagerId, trajetId);
        return ResponseEntity.ok(demande);
    }

    @GetMapping
    public List<Demande> getAllDemandes() {
        return demandeService.getAllDemandes();
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<Demande> updateStatut(@PathVariable Long id,
                                                @RequestParam String statut) {
        Demande updated = demandeService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/passager/{passagerId}")
    public List<Demande> getDemandesByPassager(@PathVariable Long passagerId) {
        return demandeService.getDemandesByPassager(passagerId);
    }

    @GetMapping("/trajet/{trajetId}")
    public List<Demande> getDemandesByTrajet(@PathVariable Long trajetId) {
        return demandeService.getDemandesByTrajet(trajetId);
    }
}
