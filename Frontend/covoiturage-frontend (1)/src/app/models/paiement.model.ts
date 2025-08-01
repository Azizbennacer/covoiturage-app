export interface Paiement {
  id: number;
  utilisateur_id: number;
  demande_id: number;
  montant: number;
  date_paiement: Date;
}