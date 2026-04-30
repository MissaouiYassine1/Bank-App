# BanqueApp

> Application web de gestion bancaire CRUD — clients et comptes — construite avec Spring Boot et Thymeleaf.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2%20Database-0046B8?style=for-the-badge&logo=h2&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License: MIT](https://img.shields.io/badge/Licence-MIT-yellow.svg?style=for-the-badge)

---

## 📋 Table des Matières

- [À Propos](#-à-propos)
- [Fonctionnalités](#-fonctionnalités)
- [Technologies Utilisées](#-technologies-utilisées)
- [Architecture du Projet](#-architecture-du-projet)
- [Installation](#-installation)
- [Utilisation](#-utilisation)
- [Roadmap](#-roadmap)
- [Contribution](#-contribution)
- [Licence](#-licence)
- [Contact](#-contact)

---

## 🚀 À Propos

**BanqueApp** est une application web académique développée dans le cadre du cours **JPA / Spring Boot**. Elle permet d'administrer une base de clients bancaires et leurs comptes associés au travers d'une interface moderne, responsive et intuitive.

### Objectifs pédagogiques

- Mettre en œuvre les relations **JPA** (`@OneToMany` / `@ManyToOne`) entre entités `Client` et `Compte`
- Développer un **CRUD complet** pour chaque entité
- Appliquer une **architecture en couches** (Entity → Repository → Service → Controller → View)
- Intégrer des technologies frontend avancées : **AJAX**, **SweetAlert**, **jQuery UI Autocomplete**
- Offrir une expérience utilisateur fluide et professionnelle

---

## ✨ Fonctionnalités

### 👤 Gestion des Clients

| Action | Détail |
|--------|--------|
| ➕ Ajout | Formulaire avec validation (nom, prénom) |
| 📋 Liste | Tableau Bootstrap responsive |
| ✏️ Modification | Formulaire pré-rempli |
| 🗑️ Suppression | Confirmation via **SweetAlert** + requête **AJAX** (sans rechargement de page) |

### 💳 Gestion des Comptes

| Action | Détail |
|--------|--------|
| ➕ Ajout | Formulaire avec **autocomplete** jQuery UI pour le client propriétaire |
| 📋 Liste | Tableau affichant RIB, solde et client associé |
| ✏️ Modification | Mise à jour du solde et du propriétaire |
| 🗑️ Suppression | Confirmation via **SweetAlert** + requête **AJAX** |

### 🎨 Interface Utilisateur

- Design **Bootstrap 5.3** entièrement responsive (mobile, tablette, desktop)
- Page d'accueil avec **statistiques dynamiques** (nombre de clients et de comptes)
- Animations fluides : hover, apparition, transitions CSS
- Ombres portées, bordures arrondies et dégradés pour un rendu professionnel

---

## 🛠 Technologies Utilisées

### Backend

| Technologie | Rôle |
|-------------|------|
| [Spring Boot 3.2](https://spring.io/projects/spring-boot) | Framework principal |
| [Spring Data JPA](https://spring.io/projects/spring-data-jpa) | ORM et persistance |
| [H2 Database](https://www.h2database.com/) | Base de données (mode fichier persistant) |
| [Jakarta Validation](https://beanvalidation.org/) | Validation des données |
| [Lombok](https://projectlombok.org/) | Réduction du code boilerplate |
| [Maven](https://maven.apache.org/) | Gestion des dépendances |

### Frontend

| Technologie | Rôle |
|-------------|------|
| [Thymeleaf](https://www.thymeleaf.org/) | Moteur de templates |
| [Bootstrap 5.3](https://getbootstrap.com/) | Framework CSS |
| [Bootstrap Icons](https://icons.getbootstrap.com/) | Bibliothèque d'icônes |
| [jQuery 3.7](https://jquery.com/) | Manipulation DOM & AJAX |
| [jQuery UI Autocomplete](https://jqueryui.com/autocomplete/) | Auto-complétion des champs |
| [SweetAlert](https://sweetalert.js.org/) | Alertes de confirmation stylisées |
| [Google Fonts](https://fonts.google.com/) | Polices Inter & Poppins |

---

## 📐 Architecture du Projet

```
src/
└── main/
    ├── java/com/banque/
    │   ├── entities/          ← Entités JPA (Client, Compte)
    │   ├── dto/               ← Objets de transfert de données (DTO)
    │   ├── repositories/      ← Interfaces Spring Data JPA
    │   ├── services/          ← Interfaces métier
    │   │   └── impl/          ← Implémentations des services
    │   └── controllers/       ← Contrôleurs Spring MVC
    │
    └── resources/
        ├── templates/         ← Vues Thymeleaf
        │   ├── fragments/     ← Header, Footer (réutilisables)
        │   ├── clients/       ← Pages CRUD Client
        │   └── comptes/       ← Pages CRUD Compte
        ├── static/
        │   ├── css/           ← Styles personnalisés
        │   └── js/            ← Scripts JavaScript
        └── application.properties
```

### Schéma de la relation JPA

```
┌──────────────────┐                    ┌────────────────────┐
│      Client      │                    │       Compte       │
├──────────────────┤                    ├────────────────────┤
│ id       (Long)  │◄───────────────────│ client_id   (FK)   │
│ nom      (String)│    @ManyToOne      │ rib       (String) │
│ prenom   (String)│                    │ solde     (double) │
└──────────────────┘                    └────────────────────┘
        │
        │  @OneToMany
        └──── possède plusieurs Comptes
```

---

## ⚙️ Installation

### Prérequis

- **Java 17** ou supérieur
- **Maven 3.8** ou supérieur
- **Git** (optionnel)

### Étapes

```bash
# 1. Cloner le dépôt
git clone https://github.com/votre-pseudo/banqueapp.git

# 2. Accéder au dossier
cd banqueapp

# 3. Compiler le projet
mvn clean install

# 4. Lancer l'application
mvn spring-boot:run
```

> ✅ L'application sera accessible à l'adresse : **http://localhost:8080**

---

## 🖥 Utilisation

### Pages disponibles

| Page | URL | Description |
|------|-----|-------------|
| 🏠 Accueil | `/` | Page d'accueil avec statistiques |
| 👥 Clients | `/clients` | Liste de tous les clients |
| ➕ Nouveau Client | `/clients/add` | Formulaire d'ajout |
| ✏️ Modifier Client | `/clients/edit/{id}` | Formulaire de modification |
| 💳 Comptes | `/comptes` | Liste de tous les comptes |
| ➕ Nouveau Compte | `/comptes/add` | Formulaire avec autocomplete client |
| ✏️ Modifier Compte | `/comptes/edit/{rib}` | Formulaire de modification |
| 🗄️ Console H2 | `/h2-console` | Console base de données |

### Accès à la console H2

```
URL JDBC    : jdbc:h2:file:./data/banquedb
Utilisateur : sa
Mot de passe: (laisser vide)
```

### Suppression AJAX

1. Cliquer sur l'icône 🗑️ dans le tableau
2. Confirmer la suppression dans la popup **SweetAlert**
3. La ligne disparaît **sans rechargement de la page**

---

## 📍 Roadmap

### ✅ Réalisé

- [x] CRUD complet — Clients (ajout, modification, suppression, liste)
- [x] CRUD complet — Comptes (ajout, modification, suppression, liste)
- [x] Relation JPA `@ManyToOne` / `@OneToMany` (Client ↔ Comptes)
- [x] Suppression AJAX avec confirmation SweetAlert
- [x] Auto-complétion client (jQuery UI)
- [x] Interface Bootstrap 5.3 responsive
- [x] Animations et design moderne
- [x] Page d'accueil avec statistiques dynamiques
- [x] Validation des formulaires (Jakarta Validation)
- [x] Architecture en couches avec DTO

### 🔜 À venir

- [ ] Pagination des listes (clients et comptes)
- [ ] Recherche avancée avec filtres dynamiques
- [ ] Authentification et gestion des rôles (Spring Security)
- [ ] Export des données en PDF et Excel
- [ ] Tests unitaires et d'intégration (JUnit, Mockito)
- [ ] Dockerisation de l'application
- [ ] Pipeline CI/CD (GitHub Actions)

---

## 🤝 Contribution

Les contributions sont les bienvenues ! Suivez le **GitHub Flow** :

1. **Forkez** le projet
2. Créez votre branche de fonctionnalité
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. Committez vos modifications
   ```bash
   git commit -m 'feat: add AmazingFeature'
   ```
4. Pushez sur la branche
   ```bash
   git push origin feature/AmazingFeature
   ```
5. Ouvrez une **Pull Request**

### Conventions de code

- Respecter l'**architecture en couches** (Entity → Repository → Service → Controller)
- Utiliser des **DTO** pour tous les échanges entre couches
- Nommer les méthodes de façon **cohérente** (tout en français ou tout en anglais, sans mélange)
- Documenter les méthodes complexes avec des **Javadoc**

---

## 📄 Licence

Distribué sous la licence **MIT**. Voir le fichier [`LICENSE`](./LICENSE) pour plus d'informations.

```
MIT License — Copyright (c) 2026 BanqueApp
```

---

## ✉️ Contact

Développeur — [MISSAOUI Yassine](https://www.linkedin.com/in/missaoui-yassine-m1y/) — yassine.missaoui@enis.tn

Lien du projet : [https://github.com/MissaouiYassine1/Bank-App](https://github.com/MissaouiYassine1/Bank-App)

---

<p align="center">
  Développé avec ❤️ dans le cadre du cours <strong>JPA / Spring Boot</strong>
</p>
