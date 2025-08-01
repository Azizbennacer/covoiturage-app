import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TrajetService } from '../../services/trajet.service';
import { Trajet } from '../../models/trajet.model';
import { MessageService } from '../../services/message.service';


@Component({
  selector: 'app-resultats-recherche',
  templateUrl: './recherche-cov.component.html',
  styleUrls: ['./recherche-cov.component.css']
})
export class RechercheResultatsComponent implements OnInit {
  resultats: Trajet[] = [];

  showMessagePopup: boolean = false;
  destinataireNom: string = '';
  messageEnCours: string = '';
  messageTexte: string = '';
  trajetPourMessage!: Trajet;
  nomConducteurPourMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private trajetService: TrajetService,
    private messageService: MessageService

  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const { villeDepart, villeArrivee, date } = params;

      if (villeDepart && villeArrivee && date) {
        const recherche = { villeDepart, villeArrivee, date };
        this.trajetService.rechercherTrajets(recherche).subscribe({
          next: (data) => this.resultats = data,
          error: (err) => console.error('Erreur recherche trajet', err)
        });
      }
    });
  }

  contacterConducteur(trajet: Trajet) {
    this.trajetPourMessage = trajet;
    this.nomConducteurPourMessage = trajet.conducteur?.nom || 'Conducteur';
    this.showMessagePopup = true;
  }

  ouvrirPopupMessagerie(trajet: Trajet) {
    this.trajetPourMessage = trajet;
    console.log("Nom du conducteur :", trajet.conducteur?.nom);
    this.messageEnCours = '';
    this.showMessagePopup = true;
  }

  fermerPopupMessagerie() {
    this.showMessagePopup = false;
  }

  envoyerMessage() {
    if (!this.messageEnCours.trim()) {
      alert("Le message ne peut pas être vide");
      return;
    }

    const expediteurId = Number(localStorage.getItem('userId'));
    const destinataireId = this.trajetPourMessage.conducteur?.id;

    if (!expediteurId || !destinataireId) {
      alert("Erreur : utilisateur non connecté ou destinataire inconnu.");
      return;
    }

    this.messageService.envoyerMessage(expediteurId, destinataireId, this.messageEnCours).subscribe({
      next: (msg) => {
        console.log("Message envoyé avec succès :", msg);
        alert("Message envoyé !");
        this.fermerPopupMessagerie();  // ou fermerPopup() selon le nom réel
      },
      error: (err) => {
        console.error("Erreur lors de l'envoi :", err);
        alert("Échec de l'envoi du message.");
      }
    });
  }

}
