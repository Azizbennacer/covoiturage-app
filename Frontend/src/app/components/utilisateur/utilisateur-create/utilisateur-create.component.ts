import { Component, Output, EventEmitter } from '@angular/core';
import { UtilisateurService } from '../../../services/utilisateur.service';
import { Utilisateur } from '../../../models/utilisateur.model';


@Component({
  selector: 'app-utilisateur-create',
  templateUrl: './utilisateur-create.component.html',
  styleUrls: ['./utilisateur-create.component.css']
})
export class UtilisateurCreateComponent {
  utilisateur: Utilisateur = {
    id: 0,
    nom: '',
    prenom: '',
    email: '',
    motDePasse: '',
    date_inscription: '',
    localisation: null
  };


  constructor(private utilisateurService: UtilisateurService) { }

  @Output() utilisateurAjoute = new EventEmitter<void>();

  onSubmit(): void {
    this.utilisateurService.createUtilisateur(this.utilisateur).subscribe({
      next: (res) => {
        console.log('Utilisateur ajouté :', res);
        alert('Ajouté ✅');
        this.utilisateurAjoute.emit(); // 🔥 Émettre l'événement
      },
      error: (err) => {
        console.error('Erreur :', err);
      }
    });
  }
}

