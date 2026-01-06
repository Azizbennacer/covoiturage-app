import { Component, OnInit } from '@angular/core';
import { MessageService, Message } from '../../../services/message.service';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {

  messages: Message[] = [];
  userId: number = Number(localStorage.getItem('userId'));

  showMessagePopup: boolean = false;
  nomConducteurPourMessage: string = '';
  messageEnCours: string = '';
  destinataireId: number | null = null;

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
    const id = localStorage.getItem('userId');
    if (id) {
      this.userId = Number(id);
      this.messageService.getMessagesRecus(this.userId).subscribe({
        next: msgs => this.messages = msgs,
        error: err => console.error(err)
      });
    } else {
      console.error("Aucun userId trouvé dans localStorage");
    }
  }

  // ✅ Quand on clique sur "Répondre"
  ouvrirPopupReponse(msg: Message): void {
    this.messageEnCours = '';
    this.nomConducteurPourMessage = msg.expediteur.nom;
    this.destinataireId = msg.expediteur.id;
    this.showMessagePopup = true;
  }

  // ✅ Ferme la popup
  fermerPopupMessagerie(): void {
    this.showMessagePopup = false;
    this.messageEnCours = '';
    this.nomConducteurPourMessage = '';
    this.destinataireId = null;
  }

  // ✅ Envoie le message via popup
  envoyerMessage(): void {
    const expediteurId = Number(localStorage.getItem('userId'));

    if (!this.messageEnCours.trim()) {
      alert("Le message ne peut pas être vide.");
      return;
    }

    if (!this.destinataireId || !expediteurId) {
      alert("Erreur : identifiants manquants.");
      return;
    }

    this.messageService.envoyerMessage(expediteurId, this.destinataireId, this.messageEnCours).subscribe({
      next: () => {
        alert("Message envoyé !");
        this.fermerPopupMessagerie();
      },
      error: err => {
        console.error("Erreur lors de l'envoi :", err);
        alert("Erreur lors de l'envoi.");
      }
    });
  }
}
