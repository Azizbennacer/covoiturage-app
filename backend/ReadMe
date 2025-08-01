# Plan de Développement - Application de Covoiturage

## Technologies
- Frontend: Angular 16+
- Backend: Spring Boot 3.x
- Base de données: PostgreSQL
- Authentification: JWT
- Paiement: Mock service (Stripe en phase 2)
- Maps: Google Maps API

## Phase 1: Backend (2 semaines)
### 1. Configuration initiale
- [x] Setup projet Spring Boot
- [x] Configurer JPA/Hibernate
- [ ] Configurer Spring Security

### 2. Entités principales
- [ ] User (conducteur/passager)
  - id, nom, email, tel, password
  - voiture (modèle, places, couleur)
- [ ] RideOffer
  - id, conducteur, départ, destination
  - date/heure, prix, places dispo
- [ ] RideRequest
  - id, passager, statut (PENDING/ACCEPTED/PAID)

## Phase 2: API Core (3 semaines)
### 1. Endpoints utilisateurs
- POST /api/auth/register
- POST /api/auth/login
- GET /api/users/me

### 2. Endpoints covoiturage
- POST /api/rides/offer (créer offre)
- GET /api/rides/search?from=X&to=Y (rechercher)
- POST /api/rides/{id}/request (faire demande)

## Phase 3: Frontend Angular (4 semaines)
### 1. Pages principales
- [ ] Login/Register
- [ ] Homepage avec popup géoloc
- [ ] Recherche offres + filtres
- [ ] Formulaire création offre

### 2. Services Angular
- AuthService (login/logout)
- RideService (search/offer/request)
- PaymentService (mock)

## Phase 4: Fonctionnalités avancées
### 1. Système de paiement
- Intégration Stripe/PayPal
- Endpoint /api/payment/process

### 2. Messagerie
- WebSockets pour chat
- Entité Message

### 3. Notifications
- Systeme d'alertes temps réel
- SSE ou WebSockets

## Livrables
- Semaine 1: API Auth fonctionnelle
- Semaine 3: Recherche covoiturage
- Semaine 5: Frontend basique
- Semaine 8: Version MVP complète
