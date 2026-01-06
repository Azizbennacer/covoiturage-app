package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    //  Trouver tous les paiements d’un utilisateur donné
    List<Paiement> findByUtilisateurId(Long utilisateurId);

    //  Trouver tous les paiements liés à une demande donnée
    List<Paiement> findByDemandeId(Long demandeId);
}
