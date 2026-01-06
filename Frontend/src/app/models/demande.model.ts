export interface Demande {
  id: number;
  trajet_id: number;
  passager_id: number;
  statut: 'en_attente' | 'accepte' | 'refuse';
  date_demande: Date;
}