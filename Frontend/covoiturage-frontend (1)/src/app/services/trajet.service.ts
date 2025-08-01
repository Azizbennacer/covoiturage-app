import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Trajet } from '../models/trajet.model';
import { AuthService } from '../services/auth.service';


@Injectable({
  providedIn: 'root'
})
export class TrajetService {
  private apiUrl = 'http://localhost:8080/api/trajets';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getAllTrajets(): Observable<Trajet[]> {
    return this.http.get<Trajet[]>(this.apiUrl);
  }
  createTrajet(trajet: any): Observable<any> {
    const email = this.authService.getUserEmail();

    if (!email) {
      console.error('Email utilisateur non disponible');
      throw new Error('Email utilisateur non disponible');
    }

    if (trajet.dateTrajet && trajet.dateTrajet.length === 16) {
      trajet.dateTrajet = trajet.dateTrajet + ':00';
    }

    const urlWithEmail = `${this.apiUrl}?email=${encodeURIComponent(email)}`;

    return this.http.post(urlWithEmail, trajet);
  }

  deleteTrajet(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  getTrajetsByUserEmail() {
    const email = this.authService.getUserEmail();
    if (!email) {
      console.error('Email utilisateur non disponible');
      return;
    }
    return this.http.get<Trajet[]>(`http://localhost:8080/api/trajets/conducteur/email/${email}`);
  }

  rechercherTrajets(recherche: { villeDepart: string; villeArrivee: string; date: string }) {
    return this.http.get<Trajet[]>(
      `http://localhost:8080/api/trajets/recherche`, {
      params: {
        villeDepart: recherche.villeDepart,
        villeArrivee: recherche.villeArrivee,
        date: recherche.date
      }
    }
    );
  }
  updateTrajet(trajet: Trajet) {
    return this.http.put<Trajet>(`http://localhost:8080/api/trajets/${trajet.id}`, trajet);
  }

}
