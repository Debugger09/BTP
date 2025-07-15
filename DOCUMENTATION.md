# Documentation Utilisateur et Technique

## Table des matières
- [Introduction](#introduction)
- [Installation et déploiement](#installation-et-déploiement)
- [Connexion et authentification](#connexion-et-authentification)
- [Gestion des projets](#gestion-des-projets)
- [Gestion des devis](#gestion-des-devis)
- [Paiement des phases](#paiement-des-phases)
- [Gestion des formations et inscriptions](#gestion-des-formations-et-inscriptions)
- [Gestion des témoignages](#gestion-des-témoignages)
- [Administration](#administration)
- [FAQ](#faq)

---

## Introduction
Bienvenue sur la plateforme **Nouboudem Construction BTP SARL**. Cette documentation présente les principales fonctionnalités de l’application, ainsi que les instructions pour l’installation, l’utilisation et l’administration.

---

## Installation et déploiement

### Prérequis
- Docker et Docker Compose installés
- Accès à un terminal (Windows, Linux ou Mac)

### Déploiement rapide
```bash
git clone <url-du-repo>
cd BTP
docker-compose build
docker-compose up -d
```

- L’API est accessible sur [http://localhost:8080](http://localhost:8080)
- Le frontend est accessible sur [http://localhost:3000](http://localhost:3000)

---

## Connexion et authentification

1. Rendez-vous sur la page de connexion.
2. Saisissez votre email et votre mot de passe.
3. Cliquez sur **Se connecter**.

![Page de connexion](captures/connexion.png)

En cas d’oubli de mot de passe, contactez l’administrateur.

---

## Guide de connexion et de navigation

Pour accéder à la plateforme, ouvrez votre navigateur et rendez-vous à l’adresse du frontend (ex : http://localhost:3000). Sur la page d’accueil, cliquez sur « Se connecter » pour accéder au formulaire d’authentification. Saisissez votre email et votre mot de passe, puis validez. Une fois connecté, vous accédez à votre tableau de bord personnalisé selon votre rôle (client ou administrateur).

Le menu principal, situé en haut ou sur le côté de l’interface, permet de naviguer entre les différentes sections : Projets, Devis, Formations, Paiements, Témoignages, Administration, etc. Chaque section affiche une liste ou un tableau de bord avec les actions possibles (créer, consulter, modifier, supprimer, etc.).

---

## Gestion des projets

- **Créer un projet (administrateur)** :
  1. Accédez à la section « Projets ».
  2. Cliquez sur « Créer un projet ».
  3. Remplissez le formulaire (nom, description, client associé, etc.).
  4. Validez pour enregistrer le projet.
- **Consulter et suivre un projet (client/administrateur)** :
  1. Sélectionnez un projet dans la liste.
  2. Consultez les informations détaillées, les phases, les devis associés et l’avancement.
- **Modifier ou supprimer un projet (administrateur)** :
  1. Depuis la fiche projet, cliquez sur « Modifier » ou « Supprimer ».

---

## Gestion des devis

- **Créer un devis (administrateur)** :
  1. Depuis la fiche projet, cliquez sur « Créer un devis ».
  2. Ajoutez un ou plusieurs lots de travaux.
  3. Pour chaque lot, ajoutez des lignes (désignation, quantité, prix unitaire).
  4. Vérifiez le récapitulatif et validez.
- **Consulter ses devis (client)** :
  1. Accédez à la section « Devis ».
  2. Consultez la liste de vos devis, leur statut et les détails.
- **Modifier ou supprimer un devis (administrateur)** :
  1. Depuis la fiche devis, cliquez sur « Modifier » ou « Supprimer ».

---

## Paiement des phases

- **Effectuer un paiement (client)** :
  1. Accédez à la section « Projets ».
  2. Sélectionnez le projet concerné.
  3. Consultez la liste des phases du projet.
  4. Sélectionnez la phase à payer.
  5. Cliquez sur « Payer » et choisissez le mode de paiement.
  6. Saisissez les informations nécessaires et validez.
  7. Un message de confirmation s’affiche et le statut de la phase est mis à jour.
- **Suivre les paiements (client/administrateur)** :
  1. Accédez à la section « Paiements » ou à la fiche projet.
  2. Consultez l’historique des paiements et les statuts.

---

## Gestion des formations et inscriptions

- **Consulter les formations (tous)** :
  1. Accédez à la section « Formations ».
  2. Parcourez la liste des formations disponibles.
- **S’inscrire à une formation (visiteur/client)** :
  1. Cliquez sur la formation souhaitée.
  2. Cliquez sur « S’inscrire » et remplissez le formulaire.
  3. Validez pour enregistrer votre inscription.
- **Gérer les formations (administrateur)** :
  1. Créez, modifiez ou supprimez des formations depuis la section dédiée.
  2. Consultez la liste des inscriptions et validez ou rejetez les demandes.

---

## Gestion des témoignages

- **Soumettre un témoignage (client)** :
  1. Accédez à la section « Témoignages ».
  2. Cliquez sur « Ajouter un témoignage ».
  3. Remplissez le formulaire et validez.
- **Valider ou rejeter un témoignage (administrateur)** :
  1. Accédez à la liste des témoignages en attente.
  2. Cliquez sur « Valider » pour publier ou « Rejeter » pour refuser.

---

## Administration du système

- **Gestion des utilisateurs (administrateur)** :
  1. Accédez à la section « Utilisateurs ».
  2. Ajoutez, modifiez ou supprimez des utilisateurs.
  3. Attribuez des rôles (client, administrateur).
- **Gestion des rôles et permissions** :
  1. Définissez les droits d’accès pour chaque rôle.
  2. Contrôlez les accès aux différentes sections et fonctionnalités.
- **Supervision et configuration avancée** :
  1. Accédez aux logs, statistiques et paramètres système.
  2. Effectuez les opérations de maintenance nécessaires.

---

## Exemples de scénarios d’utilisation courants

### Créer un projet et y associer un devis
1. L’administrateur crée un projet.
2. Il accède à la fiche projet et clique sur « Créer un devis ».
3. Il ajoute les lots et lignes, puis valide le devis.

### Effectuer un paiement pour une phase de projet
1. Le client sélectionne un projet, puis une phase à payer.
2. Il effectue le paiement et reçoit une confirmation.

### S’inscrire à une formation
1. L’utilisateur consulte la liste des formations.
2. Il choisit une formation et s’inscrit via le formulaire.

### Soumettre et valider un témoignage
1. Le client soumet un témoignage.
2. L’administrateur valide ou rejette le témoignage avant publication.

---

## FAQ

**Q : Que faire si je n’arrive pas à me connecter ?**
- Vérifiez votre email et mot de passe.
- Contactez l’administrateur si le problème persiste.

**Q : Comment réinitialiser mon mot de passe ?**
- Fonctionnalité à demander à l’administrateur.

**Q : Où trouver la documentation technique ?**
- Voir le dossier `/docs` ou contactez l’équipe technique.

---

*Pour toute question ou problème, consultez la FAQ ou contactez l’administrateur de la plateforme.* 