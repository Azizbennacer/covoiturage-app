import { Component, OnInit } from '@angular/core';
import { TrajetService } from '../../../services/trajet.service';
import { AuthService } from '../../../services/auth.service';
import { Router } from '@angular/router';
import { Trajet } from '../../../models/trajet.model';


@Component({
  selector: 'app-mes-trajets',
  templateUrl: './trajet-list.component.html',
  styleUrls: ['./trajet-list.component.css']
})
export class MesTrajetsComponent implements OnInit {
  trajets: Trajet[] = [];

  showEditPopup: boolean = false;

  trajetEnCours: Trajet = {
    id: 0,
    villeDepart: '',
    villeArrivee: '',
    dateTrajet: new Date(),
    nb_places: 1,
    prix: 0,
    type_voiture: '',
    conducteur: null!
  };

  constructor(
    private trajetService: TrajetService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const email = this.authService.getUserEmail();
    console.log("Email récupéré dans le component:", email);

    if (!email) {
      console.warn("Utilisateur non connecté. Redirection vers /login.");
      this.router.navigate(['/login']);
      return;
    }

    this.loadTrajets(email);
  }

  loadTrajets(email: string): void {
    const obs = this.trajetService.getTrajetsByUserEmail();

    if (!obs) {
      console.error("L'observable est null, probablement à cause d'un email vide");
      return;
    }

    obs.subscribe({
      next: (trajets) => {
        console.log('Trajets reçus du backend:', trajets);
        this.trajets = trajets;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des trajets:', err);
      }
    });
  }

  ouvrirBoiteMail() {
  this.router.navigate(['/message-list']);
}

  modifierTrajet(trajet: Trajet): void {
    console.log('modifierTrajet appelé', trajet);
    this.trajetEnCours = { ...trajet };
    this.showEditPopup = true;
  }

  fermerPopup(): void {
    this.showEditPopup = false;
  }

  validerModification(): void {
    this.trajetService.updateTrajet(this.trajetEnCours).subscribe({
      next: () => {
        this.fermerPopup();
        const email = this.authService.getUserEmail();
        if (email) {
          this.loadTrajets(email);
        }
      },
      error: (err) => {
        console.error("Erreur lors de la modification du trajet:", err);
      }
    });
  }

  supprimerTrajet(id: number): void {
    if (confirm('Voulez-vous vraiment sudeleteTrajetpprimer ce trajet ?')) {
      this.trajetService.deleteTrajet(id).subscribe(() => {
        const email = this.authService.getUserEmail();
        if (email) {
          this.loadTrajets(email);
        }
      });
    }
  }
}
