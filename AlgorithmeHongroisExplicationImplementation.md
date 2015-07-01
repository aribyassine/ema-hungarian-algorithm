
# Principe #

Par soustraction, on crée des zéros dans la matrice. Le but est d'obtenir le plus rapidement une configuration de zéros qui respecte les contraintes suivantes :
  * il n'y a qu'un seul zéro sélectionné par ligne
  * il n'y a qu'un seul zéro sélectionné par colonne

# Meilleure valeur : minimum/maximum #

Si la meilleure valeur est la plus grande, on multiplie la matrice par -1 afin que la meilleure valeur devienne la plus petite, ce qui permet de conserver le même algorithme pour la suite.

# Création de zéro #
## Par lignes ##
Pour chaque ligne, on recherche le minimum qu'on soustrait à la ligne, ce qui permet d'avoir au moins un zéro par ligne.
## Par colonnes ##
Pour chaque colonne, on recherche le minimum qu'on soustrait à la colonne, ce qui permet d'avoir au moins un zéro par colonne.
# Sélection de zéro encadré #
On essaie de sélectionner le maximum de zéros possible (qu'on appellera zéro encadré), qui ne soient pas en conflits avec les contraintes décrites précédemment.
On gardera la configuration qui a le plus de zéros encadrés.
Si on a autant de zéros que la taille de la matrice, on a trouvé une solution, il est donc inutile de poursuivre l'algorithme.

# Marquage #

## Étape 1 ##
On marque toute ligne n'ayant pas de zéro « encadré ».

## Étape 2 ##
On marque toute colonne ayant un zéro non encadré sur une ligne marquée.

## Étape 3 ##
On marque toute ligne ayant un zéro encadré sur une colonne marquée.

## Itération ##
On répète les étapes 2 et 3 jusqu'à ce que ça ne soit plus possible. On détecte cette impossibilité en dès que le nombre de lignes et de colonnes marquées entre deux itérations ne change pas.

# Rayage #
On raie les lignes non marquées et les colonnes marquées.
On obtient alors une sous-matrice composée des éléments qui ne sont pas rayés.

# Traitement de la sous-matrice #
On recherche le plus petit nombre dans la sous-matrice.
Dans la matrice principale :
  * on retranche ce nombre aux éléments qui ne sont pas rayés.
  * on ajoute ce nombre aux éléments qui sont rayés deux fois (éléments sur une ligne rayé et sur une colonne rayé)

# Recherche de solution(s) #
On recherche les solutions possibles. Si aucune solution existe, on reprend l'algorithme à l'étape de sélection de zéro encadrés.
La recherche de solution se fait à l'aide d'un arbre. Cela permet de ne pas s'arrêter à la première affectation acceptable mais d'obtenir toutes les solutions acceptables.

## Structure de l'arbre ##
La racine de l'arbre est un point de départ qui ne contient en lui-même aucune donnée. Son utilité est purement technique.
Chaque nœud de l'arbre correspond à un zéro qui n'est pas en conflit avec ses nœuds parents ou fils.
Le zéro est représenté par les coordonnées qui lui correspondent dans la matrice.

## Création de l'arbre ##
On parcourt la matrice par lignes puis pour chaque ligne par colonnes. Dès qu'on trouve un zéro :
s'il n'existe pas un zéro dans l'arbre qui serait en conflit avec celui trouvé (cf contraintes de la section principe), alors on ajoute ce zéro dans l'arbre, on passe à la ligne suivante et on rappelle la fonction.

## Arbre de zéro encadré ##
Dans le cas de l'affectation de zéros encadrés, on continue la construction de l'arbre même si une ligne n'a pas de zéro qui respecte les contraintes.

## Parcours de l'arbre à la recherche de solution ##
On fait un parcours récursif de l'arbre en profondeur. Si on atteint une profondeur égale à la taille de la matrice, c'est qu'on a trouvé une solution.
Pour l'arbre de zéros encadrés, le fait d'avoir poursuivi sa construction même si on ne trouvait pas un zéro à chaque ligne n'est pas gênant car le nœud feuille aura une profondeur inférieure à la taille de la matrice. C'est pour cette raison qu'on se base sur la profondeur de la matrice et non pas sur la valeur de l'attribut row porté par les nœuds de l'arbre.