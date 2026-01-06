import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Message {
  id: number;
  expediteur: {
    id: number;
    nom: string;
    // autres champs si besoin
  };
  destinataire: {
    id: number;
    nom: string;
    // autres champs si besoin
  };
  contenu: string;
  dateEnvoi: string;
}

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8080/api/messages';

  constructor(private http: HttpClient) { }

  getMessagesRecus(utilisateurId: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.apiUrl}/recus/${utilisateurId}`);
  }

  envoyerMessage(expediteurId: number, destinataireId: number, contenu: string): Observable<any> {
  return this.http.post(`${this.apiUrl}/envoyer`, null, {
    params: {
      expediteurId: expediteurId.toString(),
      destinataireId: destinataireId.toString(),
      contenu
    }
  });
}
}
