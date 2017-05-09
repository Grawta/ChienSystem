# ChienSystem

	Voici le dossier complet de notre ChienSystem, réalisé par BALBLANC Nathan et SMINI Yacine. Le but du projet était de créer un système permettant à plusieurs utilisateurs de communiquer entre eux à distance.
	Notre choix de conception a été d'ouvrir un serveur UDP sur un port fixe lorsque l'on se connectait à « l'application » puis d'émettre un message en broadcast sur ledit port. Ce message sera traité par les utilisateurs déjà connecté qui créeront un socket TCP « serveur »à chaque réception d'un message de connexion et enverront à l'émetteur du broadcast un message UDP lui signifiant qu'une connexion peut être ouverte. Ce dernier créera donc un socket TCP « client » qui se connectera au serveur précédemment créé, permettant une connexion entre les deux, et ce pour chaque utilisateur. Lors de la conversation, chaque message est écrit dans un fichier avant d'être envoyé, ce qui nous permet d'avoir un historique des conversations que l'on peut recharger quand on veut. Enfin, à la déconnexion sera émis un message UDP en broadcast afin de prévenir tous les autres utilisateurs.

Version

	La version de Java utilisée est la : JAVA 8.


Exécution

	Une fois le .zip de github téléchargé, il suffit d'exécuter la classe MainTest.java qui se trouve dans /ChienSystem-master/ChienSystem/src/chienSystem. On se retrouve alors devant une fenêtre nous demandant notre pseudo. Une fois ce dernier sélectionné, on peut ensuite communiquer avec d'autres utilisateurs en cliquant dessus.
Le port utilisé pour notre serveur UDP est le 15530. Vérifier qu'il n'est pas déjà alloué sinon aller le modifier en dur dans la classe NetworkGlobal.java qui se trouve dans /ChienSystem-master/ChienSystem/src/network.

Fonctionnalités
	
	Implémentées :
	- Se connecter
	- Choisir son pseudo
	- Choisir son interlocuteur
	- Envoyer des messages
	- Recevoir des messages
	- Être averti de la déconnexion des autres utilisateurs
	- Sauvegarder les conversations
	- Recharger les conversations même après un changement de 	machine
	- Se déconnecter
	
	Non Implémentées :
	- Envoi/Réception de fichier
	- Conversation de groupe


Rapport de Test

	On rencontre un erreur lors des test unitaire qui s'affiche en console : Le network est lancé sans l'accès au backGround donc le pointeur null apparaît. Ce n'est pas important lors des test car on n'utilise pas le même network et l'accès à la view n'est pas nécessaire.

- test01CreationServerSocketTCP → Succès
- test02CreationClientSocketTCP → Succès
- test03CreationUser → Succès
- test04CreationUserList → Succès
- test05SuppressionUserEtIsEmpty → Succès
- test06EcritureFichier → Succès
- test07CreationMessage → Succès
- test08CreationControlMessage → Succès
- test09NetworkGlobal → Succès
- test10Controller → Succès

Test de groupe
	
	Nous avons effectué des testdes fonctions citées si dessus avec d'autres groupes et les tests se sont revelés positifs.

