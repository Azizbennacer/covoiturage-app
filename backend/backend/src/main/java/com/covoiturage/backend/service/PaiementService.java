package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Paiement;
import com.covoiturage.backend.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {

    private final PaiementRepository paiementRepository;

    @Autowired
    public PaiementService(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> getPaiementById(Long id) {
        return paiementRepository.findById(id);
    }

    public List<Paiement> getPaiementsByUtilisateur(Long utilisateurId) {
        return paiementRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Paiement> getPaiementsByDemande(Long demandeId) {
        return paiementRepository.findByDemandeId(demandeId);
    }
}
