package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Demande;
import com.covoiturage.backend.model.Trajet;
import com.covoiturage.backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

    // Trouver toutes les demandes faites par un utilisateur
    List<Demande> findByPassager(Utilisateur passager);

    // Trouver toutes les demandes pour un trajet donné
    List<Demande> findByTrajet(Trajet trajet);

    // Trouver les demandes par statut
    List<Demande> findByStatut(String statut);
}
