# Bienvenue sur l'application implémentant les algorithmes de la théorie des graphes.

Pour exécuter les algorithmes vous devrez d'abord importer le graphe:
    - sous forme de matrice d'adjacente
    - saisie dans la console
    - importation d'un fichier texte contenant la matrice

Exemple du contenu d'un fichier:
    5 10 0 0 0 0 <-- 1ère case étant le nombre de sommets et la 2eme case le nombre d'arcs / arêtes
    0 0 1 0 1 0
    0 1 0 1 0 0
    0 0 1 0 1 1
    0 1 0 1 0 0
    0 0 0 1 0 0

S'il y'a un arc ou une arête entre 2 sommets i et j alors la case (i, j) de la matrice est égale à 1, sinon 0.

Sous forme de tableaux FS et APS :
Exemple:
    12 1 2 3 0 1 2 3 0 1 2 3 0 <--FS
    3 1 5 9 <-- APS
    La première case de FS contient le nombre d'arcs / arêtes et le nombre de sommets du graphe

Sous forme d'arcs / arêtes :
    Exemple:
    8 <-- nombre de sommets du graphe
    7 <-- nombre d'arcs / arêtes du graphe
    1 3 1 <-- 1ère arc / arête
    2 3 2 <-- 2ème arc / arête
    3 5 3
    3 6 1
    3 4 2
    4 8 3 <-- avant-dernier arc / arête
    4 7 3 <-- dernier arc / arête

Chaque ligne contient trois valeurs représentant respectivement la 1ère extrémité de l'arc / arête, la 2ème extrémité de l'arc / arête et le poids (ou le coût / durée de la tâche dans le cas de l'ordonnancement) de l'arc / arête.

Pour exécuter l'algorithme de décodage de Prufer, il suffit d'importer le fichier texte contenant le codage de Prufer du graphe.

    Exemple du contenu du fichier:
    6 <-- le nombre de valeur dans le tableau de codage
    3 3 3 3 4 4 <-- les valeurs du tableau du codage doit être séparées par des espaces.
