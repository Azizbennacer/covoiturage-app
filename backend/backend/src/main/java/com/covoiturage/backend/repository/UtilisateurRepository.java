package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // 👉 Ajoute cette ligne :
    Utilisateur findByEmail(String email);

}
