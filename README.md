## l2s4-projet-2023

# Equipe

- Camille BARTHELEMY
- Barbara JOYEZ
- Anis-Islam REZIG
- Nolwenn DELEHONTE

# Sujet

[Le sujet 2023](https://www.fil.univ-lille.fr/~varre/portail/l2s4-projet/sujet2023.pdf)

# Commandes avec terminal 

Il faut se placer dans l2s4-projet-2023/ pour executer les commandes. 

* compiler toutes les classes du projets
```
javac -classpath jars/json-20220924.jar:src -d classes src/pandemic/*.java
```
* compiler tous les tests du projets 
```
javac -classpath classes:jars/junit-4.13.2.jar test/pandemic/*.java
```
* executer Main 
```
java -cp jars/json-20220924.jar:classes pandemic.Main
```
* executer Main 
```
java -cp jars/json-20220924.jar:classes pandemic.Main
```
* executer les tests
```
java -jar jars/junit-platform-console-standalone-1.9.2.jar -cp classes:test:jars/json-20220924.jar --scan-classpath --disabled-banner
```
* générer la documentation 
```
javadoc -classpath jars/json-20220924.jar:src -d docs -subpackages pandemic
```

# Commandes avec makefile 
Il faut se placer dans l2s4-projet-2023/ pour executer les commandes. 

* compiler toutes les classes du projets 
```
make cls
```
* compiler tous les tests du projets et executer les tests
```
make test
```
* pour creer l'executable
```
make jeu.jar
```
* générer la documentation 
```
make doc
```
* retirer tous les documents de classe et la documentation
```
make clean
```
* genere les .class et crées l'executable
```
make 
```
* pour executer le jar (ATTENTION PAS DE PARAMETRES ET DOSSIER jars)
```
java -jar jars/jeu.jar 
```


# Livrables

## Livrable 1

### Atteinte des objectifs

* diagramme UML (à modifer par la suite)
* code classe Maps et City 
* test CityTest

### Difficultés restant à résoudre

(probleme de package sur eclipse qui nous empeche de continuer ces derniers points)
* JSON dans la classe Maps
* Ajout d'une méthode qui permet de savoir quand la partie est finie dans la classe Maps 
* Test Maps
* Main


## Livrable 2

### Atteinte des objectifs

* diagramme UML (à modifer par la suite)
* code des classes Players, Cards et Roles 
* Test pour toutes les classes

( Il y a deux main :
* --> PandemicMain : principale main
* --> Main : main pour verifier les differents comportements )

### Difficultés restant à résoudre

* normalement tout a été gérer pour ce livrable

## Livrable 3

### Atteinte des objectifs

* (UML est en deux parties pour une meilleur qualitée d'image)
* creation du plateau de jeu à partir d'un fichier json
* creation des 4 joueurs avec chacun un rôle différent
* chacun des joueurs est placés sur une ville aléatoire
* les joueurs peuvent effectuées les actions
* les affichages sont effectuées
* a la fin du main nous avons crée des actions (a la main) pour montrer leurs fonctionnements voir "autres actions"

### Difficultés restant à résoudre

* déroulement de la méthode play
* quelques modifications a apporter aux actions pour améliorer leur fonctionnement 
* certaines actions ne sont pas totalement possibles(move --> une fois que le player move apres il bouge toujours sur la meme ville et research, build, pass et treat --> ok)

## Livrable 4

### Atteinte des objectifs

* résolution du problème avec move
* amélioration de l'affichage
* gestion de la fin du jeu
* toutes les actions sont possibles
* le taux d'infection est géré
* amélioration de la documentation pour les actions

### Difficultés restant à résoudre

* fin du jeu 

# Journal de bord

## Semaine 1

* Début des UML de la classe City et de la classe Maps
* Début du code pour les deux classes

## Semaine 2

* Suite UML City et Maps et du code 
* Ajout de classe dans UML  qui nous semblent indispensable notamment la classe Disease et d'autres pour les rôles, game... 
* Ajout JSON au projet 
* Verification si les differents comportement attendues sont bien gérés dans nos classes 
* Début des tests

## Semaine 3

* Amelioration de l'UMl au niveau des classes City et Maps
* Progression des tests pour City 
* Lecture JSON (début)
* Création du Main

## Semaine 4

* Bon fonctionnement du JSON et test du JSON
* Création des packages cards et roles et de la classe player
* Modifications de l'UML

## Semaine 5

* Correction du code suite au rendu du Livrable 1
* Ajout de plusieurs maladies sur une ville
* Réflexion du la modélisation des cards
* Modification de l'UML pour les cards
* Fin de la modélisation des rôles (validé sur discord)
* Test des commandes de compilation pour le prochain livrable
* ajout des methodes stack et unstack à la classe pile

## Semaine 6

* amelioration uml
* fin de la classe pile (ajout de la methode mixCards)
* tests unitaires de la classe PileOfCards
* méthode pour les déplacements des joueurs de Player
* début des tests de Player

## Semaine 7

* implementaion des Piles
* realisation du test de la creation de la carte avec les piles dans le fichier pandemicmain.java (il suffit d'executer le fichier).
* ajout des stations dans Maps
* écriture des methodes pour les cartes dans la main du joueur, pour les actions construire une station, traiter une maladie... et pour savoir si ces actions sont possibles de Player
* ajout des tests dans Maps, Players
* creation Main pour verifier les comportements
* débeugage et ajout des cartes epidemies à la pile des cartes joueurs dans le main

## Semaine 8

* travail sur l'uml
* travail sur les action dans la classe joueur
* début du package des actions
* on a revu notre modélisation car au début on avait mis player et rôle séparément

## Semaine 9

* modification et fin du package des actions
* modification dans game avec l'ajout des fonctions d'affichage
* création des cartes et des piles avec l'initialisation du jeu
* màj de l'uml
* objectif : avancer sur game, gérer le nombre d'action


## Semaine 10

* début de la méthode play
* début des fonctions d'affichage
* création d'une liste d'action dans les joueurs
* màj de l'uml
* on a gérer le nombre d'action
* remise en cause de notre modélisation mais finalement on a trouvé une façon de régler notre problème avec les actions et les players
* tests de game
* tests des actions
* création des fonctions pour les cartes dans maps (avant tout se déroulait dans le main)

## Semaine 11

* résolution du problème de move
* début de la gestion de la fin du jeu
* toutes les actions sont possibles
* amélioration de la documentation pour les actions

## Semaine 12

* modification des tests
* amélioration de l'affichage
* le taux d'infection est géré
* préparation du diapo pour la soutenance
* fin du jeu 
* mise en place du depot 
