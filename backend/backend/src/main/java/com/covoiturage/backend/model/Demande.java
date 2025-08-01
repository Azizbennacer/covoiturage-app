package com.covoiturage.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Trajet trajet;

    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Utilisateur passager;

    private String statut;

    @Column(name = "date_demande")
    private LocalDateTime dateDemande;

    // ====== Getters & Setters ======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Utilisateur getPassager() {
        return passager;
    }

    public void setPassager(Utilisateur passager) {
        this.passager = passager;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
}
