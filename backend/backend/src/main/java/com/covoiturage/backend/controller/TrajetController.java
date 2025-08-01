package com.covoiturage.backend.controller;

import com.covoiturage.backend.model.Trajet;
import com.covoiturage.backend.model.Utilisateur;
import com.covoiturage.backend.repository.TrajetRepository;
import com.covoiturage.backend.repository.UtilisateurRepository;
import com.covoiturage.backend.service.TrajetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trajets")
@CrossOrigin(origins = "*")
public class TrajetController {

    private final TrajetService trajetService;
    private final TrajetRepository trajetRepository;
    private final UtilisateurRepository utilisateurRepository;

    // 🔧 Injection des 3 dépendances
    public TrajetController(
            TrajetService trajetService,
            TrajetRepository trajetRepository,
            UtilisateurRepository utilisateurRepository) {
        this.trajetService = trajetService;
        this.trajetRepository = trajetRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // 🔹 GET ALL
    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetService.getAllTrajets();
    }

    // 🔹 POST : Créer un nouveau trajet avec conducteur lié
    @PostMapping
    public ResponseEntity<?> createTrajet(
            @RequestBody Trajet trajet,
            @RequestParam String email) {

        Utilisateur conducteur = utilisateurRepository.findByEmail(email);
        if (conducteur == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Utilisateur (conducteur) non trouvé pour l'email : " + email);
        }

        trajet.setConducteur(conducteur);

        try {
            Trajet nouveau = trajetRepository.save(trajet); // appel direct au repository
            return ResponseEntity.ok(nouveau);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du trajet : " + e.getMessage());
        }
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Trajet>> rechercherTrajets(
            @RequestParam String villeDepart,
            @RequestParam String villeArrivee,
            @RequestParam String date
    ) {
        try {
            LocalDate dateOnly = LocalDateTime.parse(date).toLocalDate();

            List<Trajet> resultats = trajetRepository.findByVilleDepartAndVilleArriveeAndDate(
                    villeDepart, villeArrivee, dateOnly
            );

            return ResponseEntity.ok(resultats);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    // 🔹 GET : Récupérer un trajet par email du conducteur
    @GetMapping("/conducteur/email/{email}")
    public ResponseEntity<List<Trajet>> getTrajetsByConducteurEmail(@PathVariable String email) {
        List<Trajet> trajets = trajetRepository.findByConducteurEmail(email);
        return ResponseEntity.ok(trajets);
    }

    // 🔹 PUT : Mettre à jour un trajet
    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody Trajet updatedTrajet) {
        return trajetService.updateTrajet(id, updatedTrajet)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 DELETE : Supprimer un trajet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        if (trajetService.deleteTrajet(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
