package com.covoiturage.backend.service;

import com.covoiturage.backend.model.Utilisateur;
import com.covoiturage.backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    //  Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    //  Récupérer un utilisateur par ID
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    //  Créer un utilisateur
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    //  Mettre à jour un utilisateur
    public Optional<Utilisateur> updateUtilisateur(Long id, Utilisateur details) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setNom(details.getNom());
            utilisateur.setPrenom(details.getPrenom());
            utilisateur.setEmail(details.getEmail());
            utilisateur.setMotDePasse(details.getMotDePasse());
            utilisateur.setTelephone(details.getTelephone());
            utilisateur.setRole(details.getRole());
            utilisateur.setLocalisation(details.getLocalisation());
            utilisateur.setDateInscription(details.getDateInscription());
            return utilisateurRepository.save(utilisateur);
        });
    }

    //  Supprimer un utilisateur
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
