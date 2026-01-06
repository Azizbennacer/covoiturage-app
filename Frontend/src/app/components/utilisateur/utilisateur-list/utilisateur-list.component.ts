import { Component, OnInit, Input } from '@angular/core';
import { UtilisateurService } from '../../../services/utilisateur.service';
import { Utilisateur } from '../../../models/utilisateur.model';

@Component({
  selector: 'app-utilisateur-list',
  templateUrl: './utilisateur-list.component.html',
  styleUrls: ['./utilisateur-list.component.css']
})
export class UtilisateurListComponent implements OnInit {
  utilisateurs: Utilisateur[] = [];

  constructor(private utilisateurService: UtilisateurService) {}
  chargerUtilisateurs(): void {
    this.utilisateurService.getUtilisateurs().subscribe(data => {
      this.utilisateurs = data;
    });
  }
  ngOnInit(): void {
    this.chargerUtilisateurs();
    this.utilisateurService.getUtilisateurs().subscribe(
      data => {
        this.utilisateurs = data;
        console.log(data);
      },
      error => {
        console.error('Erreur lors du chargement des utilisateurs :', error);
      }
    );
  }
  @Input() rafraichir: any;

}
