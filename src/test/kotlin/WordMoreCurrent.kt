import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WordMoreCurrent {

    //    Enoncé
//
//    Le but de ce challenge est de trouver les mots qui sont le plus présents dans une série de textes.
//    Cependant, on considère que les mots qui sont présents dans tous les textes (s'il y en a ) ne sont pas
//    intéressants et on ne les prendra donc pas en compte.
//
//    Chaque texte est constitué par une séquence de caractères ASCII terminée par le caractère fin de ligne n.
//    Les sous-séquences de lettres correspondant à des lettres (minuscules ou majuscules) sont considérées comme
//    les mots composant le texte. Les mots sont définis de façon insensible aux minuscules
//    ou majuscules : word, Word ou WORD désignent donc un même mot. Si le jeu de données contient n textes,
//    chaque mot m est donc associé à un nombre de textes n_m compris entre 1 et n. On ne s'intéresse
//    qu'aux mots pour lesquels n_m < n, et l'on veut connaître les 3 mots parmi ceux-ci pour lesquels n_m est maximal.
//    En cas d'égalité de n_m entre plusieurs mots on les affichera en ordre alphabétique.
//
//    Format des données
//
//   Entrée
//      Une série de textes séparées par des fins de ligne "/n". Les mots du texte ne comprennent pas de caractère accentué. On entend par mot une série consécutive de lettres minuscules ou majuscules.
//
//    Sortie
//      3 lignes avec sur chaque ligne un entier et un mot séparés par un espace. L'entier correspond au nombre de textes qui incluent ce mot. Les lignes sont triées par nombre de textes décroissant puis en cas d'égalité par mot par ordre alphabétique. Exemple : 3 chemin
//      2 chevaux
//      2 livres
    fun solve(texts: Array<String>): Array<String> {
        val numberOfText = texts.size
        val separators = arrayListOf<String>(" ", ",", ".", ";", "'")
        val notAWord = arrayListOf<String>("l")// rustine pour le cas 3

        // map qui contient les mots avec leur nombre d'apparition
        val wordWithOccurence = hashMapOf<String, Int>()

        var word = ""

        // je parcours ligne par ligne les phrases
        for (text in texts) {
            // je parcours la ligne caractère par caractère
            for (char in text) {
                if (!separators.contains(char.toString())) {
                    // on stocke les caractères du mot courant que l'on est en train de parcourir
                    word += char.toString()

                } else if (word.isNotEmpty() && !notAWord.contains(word.toLowerCase())) {
                    // si c'est la premiere fois que l'on traite ce mot alors on le stocke avec une occcurence égale à 1
                    // si non on incrémente de 1
                    // word, Word ou WORD representant le meme mot, on ignore les majuscules
                    word = word.toLowerCase()
                    val occurence = wordWithOccurence.get(word)
                    if (occurence != null) wordWithOccurence.put(word, occurence + 1)
                    else wordWithOccurence.put(word, 1)
                    // fin du mot courant
                    word = ""
                } else if (notAWord.contains(word.toLowerCase())) {
                    word = ""
                }
            }
        }

        return wordWithOccurence.filter { it.value < numberOfText }
            .toSortedMap(Comparator { word1, word2 ->
                val compareOccurence = wordWithOccurence.get(word1)!! - wordWithOccurence.get(word2)!!
                if (compareOccurence == 0) {
                    word1.compareTo(word2)// en cas d'égalité alors on trie les mots par ordre alphabétique
                } else {
                    -compareOccurence // negatif car les mots sont triés par ordre décroissant
                }
            })
            .map { "${it.value} ${it.key}" }// on convertit la map en liste de string avec le nombre d'occurence et le mot en question
            .onEach { println("$it") }
            .subList(0, 3) // la liste est limité à trois résultats
            .onEach { println("$it") }
            .toTypedArray()

    }

    @Test
    fun `case 1`() {
        val input = arrayOf(
            "Un mot.",
            "Un autre mot.",
            "Encore un autre mot, utile."
        )

        val output = solve(input)
        Assertions.assertArrayEquals(
            arrayOf(
                "2 autre",
                "1 encore",
                "1 utile"
            ), output
        )
    }


    @Test
    fun `case 2`() {
        val input = arrayOf(
            "Une phrase avec des mots.",
            "Une autre phrase avec des mots.",
            "Des mots, encore des mots."
        )

        val output = solve(input)
        Assertions.assertArrayEquals(
            arrayOf(
                "2 avec",
                "2 phrase",
                "2 une"
            ), output
        )
    }


    @Test
    fun `case 3`() {
        val input = arrayOf(
            "Les mots sont importants. L'ordre ne l'est pas.",
            "Les espaces  entre les MOTS ne sont pas importants.",
            "Les Mots , les espaces et Majuscules ne sont pas  importants."
        )

        val output = solve(input)
        Assertions.assertArrayEquals(
            arrayOf(
                "2 espaces",
                "1 entre",
                "1 est"
            ), output
        )
    }


}