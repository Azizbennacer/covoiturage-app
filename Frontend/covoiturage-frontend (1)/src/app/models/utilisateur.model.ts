export interface Utilisateur {
  id: number;
  nom: string;
  prenom: string;
  email: string;
  motDePasse: string;
  telephone?: string;
  role?: string;
  localisation : string;
  date_inscription
}