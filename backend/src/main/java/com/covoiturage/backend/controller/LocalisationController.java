package com.covoiturage.backend.controller;

import com.covoiturage.backend.model.Localisation;
import com.covoiturage.backend.service.LocalisationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/localisations")
@CrossOrigin(origins = "*")
public class LocalisationController {

    private final LocalisationService localisationService;

    public LocalisationController(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    @PostMapping("/maj")
    public ResponseEntity<String> mettreAJourLocalisation(@RequestParam Long utilisateurId,
                                                          @RequestParam double latitude,
                                                          @RequestParam double longitude) {
        String result = localisationService.mettreAJourLocalisation(utilisateurId, latitude, longitude);
        if (result.equals("Utilisateur non trouvé.")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Localisation> getLocalisationParUtilisateur(@PathVariable Long id) {
        Optional<Localisation> localisation = localisationService.getLocalisationParUtilisateur(id);
        return localisation.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
