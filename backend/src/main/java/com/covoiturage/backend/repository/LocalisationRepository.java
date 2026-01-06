package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Localisation;
import com.covoiturage.backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {

    Optional<Localisation> findByUtilisateur(Utilisateur utilisateur);

    boolean existsByUtilisateur(Utilisateur utilisateur);
}
