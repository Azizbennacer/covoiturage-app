import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Utilisateur {
    id?: number;
    nom: string;
    email: string;
    motDePasse: string;
    telephone?: string;
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = 'http://localhost:8080/api/utilisateurs';
    constructor(private http: HttpClient) { }

    inscription(user: Utilisateur): Observable<any> {
        return this.http.post(this.apiUrl, user);
    }

    connexion(email: string, motDePasse: string): Observable<any> {
        const body = { email, motDePasse };
        return this.http.post(`${this.apiUrl}/login`, body);

    }

    getUserEmail(): string | null {
        const email = localStorage.getItem('userEmail');
        console.log("User dans getUserEmail:", email);
        return email;
    }

}
