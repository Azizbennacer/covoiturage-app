package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Trajet;
import com.covoiturage.backend.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {

    private final TrajetRepository trajetRepository;

    @Autowired
    public TrajetService(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    public Optional<Trajet> getTrajetById(Long id) {
        return trajetRepository.findById(id);
    }

    public Trajet createTrajet(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    public Optional<Trajet> updateTrajet(Long id, Trajet updatedTrajet) {
        return trajetRepository.findById(id).map(trajet -> {
            trajet.setVilleDepart(updatedTrajet.getVilleDepart());
            trajet.setVilleArrivee(updatedTrajet.getVilleArrivee());
            trajet.setDateTrajet(updatedTrajet.getDateTrajet());
            trajet.setPrix(updatedTrajet.getPrix());
            trajet.setNbPlaces(updatedTrajet.getNbPlaces());
            trajet.setTypeVoiture(updatedTrajet.getTypeVoiture());
            trajet.setConducteur(updatedTrajet.getConducteur());
            return trajetRepository.save(trajet);
        });
    }

    public boolean deleteTrajet(Long id) {
        if (trajetRepository.existsById(id)) {
            trajetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
