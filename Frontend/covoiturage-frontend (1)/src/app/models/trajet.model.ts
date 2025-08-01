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
export interface Trajet {
  id: number;
  conducteur: Utilisateur; 
  villeDepart: string;
  villeArrivee: string;
  dateTrajet: Date;
  prix: number; 
  nb_places: number;
  type_voiture : string;
}