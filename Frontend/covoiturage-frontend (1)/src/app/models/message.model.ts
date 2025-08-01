export interface Message {
  id: number;
  expediteur_id: number;
  destinataire_id: number;
  contenu: string;
  date_envoi: Date;
}