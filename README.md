# Bank Account Kata

Implémentation en Java 17 / Spring du kata Bank
Account (https://gitlab.com/exalt-it-dojo/katas-java/-/tree/main/BankAccount).

Projet développé avec lombok.

## Build & Run

### Maven

Il s'agit d'un projet maven. Il est possible de le lancer en ligne de commande ou depuis votre IDE.
Testé avec maven 3.6.0.

### Docker

Le Dockerfile build le jar et génère une image qui exécute l'application.

Lors du premier build, le téléchargement de l'image openjdk:17-alpine peut prendre quelques minutes.

Compter une minute pour le build du projet maven (en fonction de votre environnement).

- Pour build l'image docker : se placer à la racine du projet et exécuter :
  `docker build -t bank-account-kata .`
- Pour lancer l'application (écoutant sur le port 80) :
  `docker run -d -p 80:8080 -it bank-account-kata`

## Utiliser l'API

- Créer un compte :
  `curl -XPOST localhost:80/accounts` (retourne le compte créé)
- Effectuer un dépôt sur un compte : `curl -XPOST localhost:80/accounts/<id>/deposit/<amount>`
  (retourne le montant contenu sur le compte après l'opération)
- Récupérer un compte : `curl localhost:80/accounts/<id>`
- Voir l'historique des opérations d'un compté : `curl localhost:80/accounts/<id>/history`

Ci-dessous la description du kata.

## Choix d'implémentation

Quelques choix d'implémentations afin d'aller au plus simple :

- Pas de gestion des utilisateurs :
  - Un compte n'est pas associé à un client
  - Pas de sécurité : tous les clients (et même non-clients) ont accès à tous les comptes
- L'argent pousse dans les arbres : si un client veut déposer de l'argent, on le fait sans discuter et sans rien
  demander en échange
- L'argent par la fenêtre : dans la vraie vie on aimerait bien avoir de l'argent en échange d'un retrait, ici l'argent
  disparait juste du compte
- La banque ne fait pas crédit : un client ne peut pas retirer plus que ce qu'il a sur son compte
- Utilisation d'une base h2 : c'est rapide à déployer et le but n'est pas de passer en prod. Contrepartie : pas de
  persistence entre les exécutions de l'application
- Pas de requête paginée pour l'historique (simple manque de temps)
- Tests : je n'ai pas employé le TDD pour ce projet afin de me concentrer sur le pattern d'architecture hexagonale (les
  deux étant des découvertes pour moi)

--------------------------------

## Directives

Ce kata est un challenge d'architecture hexagonale, il s'implémente par étape avec un **1er focus sur le domaine
métier**.

Vos commits successifs dans Git retranscrivent vos étapes et le cycle TDD red/green/refactor si vous décidez de
l'adopter.

### Etape 1 - Le modèle métier

1ère étape est essentielle, vous vous concentrez sur le modèle métier : simple, efficace et non-anémique.

Vous l'isolez derrière les ports.

```
ATTENTION - CETTE PREMIERE ETAPE EST PRIMORDIALE
Elle devra être matérialisée proprement dans vos commits.
Elle est attendue par nos clients et ne devrait pas excéder 2h d'implémentation.
```

### Etape 2 - Adapteur API

Implémentation d'un adapteur Spring qui expose l'application en respectant les meilleurs standards d'une REST API.

### Etape 3 - Adapteur de Persistence

Implémentation d'un adapteur de persistence de votre choix (SQLlite, H2, ...).

## User Stories

```
In order to implement this Kata, think of your personal bank account experience.
When in doubt, go for the simplest solution Requirements

* Deposit and Withdrawal
* Account statement (date, amount, balance)
* Statement printing
 

## User Story 1

In order to save money

As a bank client

I want to make a deposit in my account


## User Story 2

In order to retrieve some or all of my savings

As a bank client

I want to make a withdrawal from my account


## User Story 3

In order to check my operations

As a bank client

I want to see the history (operation, date, amount, balance) of my operations
```

## Credits

Merci aux craftsmen de la SGIB pour leurs exigences qui nous tirent vers le meilleur.
