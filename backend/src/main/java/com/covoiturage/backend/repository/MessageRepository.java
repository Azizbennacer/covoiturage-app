package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Message;
import com.covoiturage.backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Trouver tous les messages envoyés par un utilisateur
    List<Message> findByExpediteur(Utilisateur expediteur);

    // Trouver tous les messages reçus par un utilisateur
    List<Message> findByDestinataire(Utilisateur destinataire);

    // Trouver la conversation entre deux utilisateurs
    List<Message> findByExpediteurAndDestinataireOrDestinataireAndExpediteur(
            Utilisateur expediteur1, Utilisateur destinataire1,
            Utilisateur expediteur2, Utilisateur destinataire2
    );

}
