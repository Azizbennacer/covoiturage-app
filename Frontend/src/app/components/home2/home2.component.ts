import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TrajetService } from '../../services/trajet.service';
import { format } from 'date-fns';
import { Trajet } from '../../models/trajet.model';

@Component({
  selector: 'app-home2',
  templateUrl: './home2.component.html',
  styleUrls: ['./home2.component.css']
})
export class Home2Component {
  // Formulaire de création
  trajet = {
    villeDepart: '',
    villeArrivee: '',
    dateTrajet: '',
    nbPlaces: 1,
    prix: 0,
    typeVoiture: ''
  };

  // Formulaire de recherche
  recherche = {
    villeDepart: '',
    villeArrivee: '',
    date: ''
  };

  // Résultats de la recherche
  resultatsRecherche: Trajet[] = [];

  showConfirmationPopup = false;
  confirmationMessage = '';

  constructor(
    private trajetService: TrajetService,
    private router: Router
  ) { }

  // Création d’un nouveau trajet
  submitTrajet() {
    if (this.trajet.dateTrajet && this.trajet.dateTrajet.length === 16) {
      this.trajet.dateTrajet = this.trajet.dateTrajet + ':00';
    }

    this.trajetService.createTrajet(this.trajet).subscribe({
      next: () => {
        this.confirmationMessage = 'Trajet créé avec succès !';
        this.showConfirmationPopup = true;
      },
      error: (err) => {
        alert('Erreur lors de la création du trajet.');
        console.error('Erreur création trajet:', err);
        console.log('Trajet envoyé au back :', this.trajet);
      }
    });
  }

  // Recherche de trajets
  rechercherTrajets() {
  console.log("Valeurs du formulaire de recherche :", this.recherche);

  if (
    !this.recherche.villeDepart.trim() ||
    !this.recherche.villeArrivee.trim() ||
    !this.recherche.date || this.recherche.date.trim() === ''
  ) {
    alert("Merci de remplir tous les champs de recherche.");
    return;
  }

  // Ajout des secondes si manquantes
  let dateFinale = this.recherche.date;
  if (dateFinale.length === 16) { // exemple: '2025-06-26T08:00'
    dateFinale += ':00';          // devient '2025-06-26T08:00:00'
  }

  const rechercheFormatee = {
    villeDepart: this.recherche.villeDepart,
    villeArrivee: this.recherche.villeArrivee,
    date: dateFinale
  };

  this.trajetService.rechercherTrajets(rechercheFormatee).subscribe({
    next: (resultats) => {
      this.resultatsRecherche = resultats;
      console.log("Résultats de recherche reçus :", resultats);
    },
    error: (err) => {
      console.error("Erreur lors de la recherche :", err);
    }
  });
  this.router.navigate(['/recherche-cov'], {
  queryParams: {
    villeDepart: this.recherche.villeDepart,
    villeArrivee: this.recherche.villeArrivee,
    date: this.recherche.date
  }
});

}


  fermerPopup(): void {
    this.showConfirmationPopup = false;
  }

  afficherTrajetsUtilisateur() {
    this.router.navigate(['/trajet-list']);
  }
}
