package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Localisation;
import com.covoiturage.backend.model.Utilisateur;
import com.covoiturage.backend.repository.LocalisationRepository;
import com.covoiturage.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalisationService {

    private final LocalisationRepository localisationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public LocalisationService(LocalisationRepository localisationRepository, UtilisateurRepository utilisateurRepository) {
        this.localisationRepository = localisationRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public String mettreAJourLocalisation(Long utilisateurId, double latitude, double longitude) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(utilisateurId);
        if (utilisateurOpt.isEmpty()) {
            return "Utilisateur non trouvé.";
        }

        Utilisateur utilisateur = utilisateurOpt.get();
        Localisation localisation = localisationRepository.findByUtilisateur(utilisateur)
                .orElse(new Localisation());

        localisation.setUtilisateur(utilisateur);
        localisation.setLatitude(latitude);
        localisation.setLongitude(longitude);

        localisationRepository.save(localisation);
        return "Localisation mise à jour avec succès.";
    }

    public Optional<Localisation> getLocalisationParUtilisateur(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .flatMap(localisationRepository::findByUtilisateur);
    }
}
