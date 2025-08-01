import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  showLoginPopup = false;
  showSignupPopup = false;
  showSuccessMessage = false;
  successMessage = '';
  isSuccess = false;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.closeAllPopups();
  }

  openPopupConnexion(): void {
    this.showLoginPopup = true;
    this.showSignupPopup = false;
  }

  openPopupInscription(): void {
    this.showLoginPopup = false;
    this.showSignupPopup = true;
  }

  closeAllPopups(): void {
    this.showLoginPopup = false;
    this.showSignupPopup = false;
  }

  submitLogin(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);

    const email = formData.get('email') as string;
    const motDePasse = formData.get('motDePasse') as string;

    if (email && motDePasse) {
      this.authService.connexion(email, motDePasse).subscribe({
        next: (response) => {
          localStorage.setItem('userEmail', email);
          localStorage.setItem('userId', response.id!.toString());
          this.closeAllPopups();
          this.successMessage = 'Connexion réussie !';
          this.isSuccess = true;
          this.showSuccessMessage = true;
          setTimeout(() => {
            this.router.navigate(['/home2']);
          }, 2000);
        },
        error: () => {
          this.closeAllPopups();
          this.successMessage = 'Email ou mot de passe incorrect.';
          this.isSuccess = false;
          this.showSuccessMessage = true;
        }
      });
    } else {
      this.closeAllPopups();
      this.successMessage = 'Tous les champs sont requis !';
      this.isSuccess = false;
      this.showSuccessMessage = true;
    }
  }

  submitSignup(event: Event): void {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);
    const email = formData.get('email') as string;
    const nom = formData.get('username') as string;
    const motDePasse = formData.get('motDePasse') as string;
    const telephone = formData.get('telephone') as string;

    if (email && nom && motDePasse && telephone) {
      this.authService.inscription({ email: email, nom, motDePasse: motDePasse, telephone }).subscribe({
        next: () => {
          this.closeAllPopups();
          this.successMessage = 'Inscription réussie !';
          this.isSuccess = true;
          this.showSuccessMessage = true;
          setTimeout(() => {
            this.router.navigate(['/home2']);
          }, 2000);
        },
        error: () => {
          this.closeAllPopups();
          this.successMessage = 'Erreur lors de l\'inscription.';
          this.isSuccess = false;
          this.showSuccessMessage = true;
        }
      });
    } else {
      this.closeAllPopups();
      this.successMessage = 'Tous les champs sont requis !';
      this.isSuccess = false;
      this.showSuccessMessage = true;
    }
  }
}
