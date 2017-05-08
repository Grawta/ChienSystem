# ChienSystem
Voici le dossier complet de notre ChienSystem. La version de Java utilisée est la : JAVA 8. 
On rencontre un erreur lors des test unitaire qui s'affiche en console : Le network est lancé sans l'accès au backGround donc le pointeur null apparait.
Tous les tests fonctionnent (10 au total) 
Nous n'effectuons pas le transfert de fichiers. La communication s'effectue en TCP entre les utilisateurs et en UDP lors d'envoie de message de connexion/deconnexion/socket_create

Le port utilisé pour notre serveur UDP est le 15530. Verifier qu'il n'est pas déjà alloué sinon aller le modifier en dur dans la classe NetworkGlobal.
