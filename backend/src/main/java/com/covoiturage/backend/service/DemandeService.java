package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Demande;
import com.covoiturage.backend.model.Trajet;
import com.covoiturage.backend.model.Utilisateur;
import com.covoiturage.backend.repository.DemandeRepository;
import com.covoiturage.backend.repository.TrajetRepository;
import com.covoiturage.backend.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final TrajetRepository trajetRepository;
    private final UtilisateurRepository utilisateurRepository;

    public DemandeService(DemandeRepository demandeRepository,
                          TrajetRepository trajetRepository,
                          UtilisateurRepository utilisateurRepository) {
        this.demandeRepository = demandeRepository;
        this.trajetRepository = trajetRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Demande creerDemande(Long passagerId, Long trajetId) {
        Optional<Utilisateur> passagerOpt = utilisateurRepository.findById(passagerId);
        Optional<Trajet> trajetOpt = trajetRepository.findById(trajetId);

        if (passagerOpt.isEmpty() || trajetOpt.isEmpty()) {
            throw new RuntimeException("Passager ou trajet introuvable");
        }

        Demande demande = new Demande();
        demande.setPassager(passagerOpt.get());
        demande.setTrajet(trajetOpt.get());
        demande.setStatut("en attente");
        demande.setDateDemande(LocalDateTime.now());

        return demandeRepository.save(demande);
    }

    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    public Demande updateStatut(Long demandeId, String statut) {
        return demandeRepository.findById(demandeId)
                .map(demande -> {
                    demande.setStatut(statut);
                    return demandeRepository.save(demande);
                })
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
    }

    public List<Demande> getDemandesByPassager(Long passagerId) {
        Utilisateur passager = utilisateurRepository.findById(passagerId)
                .orElseThrow(() -> new RuntimeException("Passager non trouvé"));
        return demandeRepository.findByPassager(passager);
    }

    public List<Demande> getDemandesByTrajet(Long trajetId) {
        Trajet trajet = trajetRepository.findById(trajetId)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé"));
        return demandeRepository.findByTrajet(trajet);
    }
}
