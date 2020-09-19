import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class XMLTest {

    //    Enoncé
//
//    Dans ce challenge vous devez convertir un fichier de type "XML" (Markup Language,
//    c'est-à-dire basé sur des paires de balises ouvrantes et fermantes) au format S-Expression
//    (Symbolic Expressions basé sur des listes parenthésées). Les jeux de données en entrée sont
//    constitués de paires de balises ouvrantes et fermantes éventuellement imbriquées de la
//    forme <xxx> pour les balises ouvrantes et </xxx> pour les balises fermantes de nom xxx.
//    Entre les balises ouvrante et fermante d'une paire (même nom), il y a zéro, une ou plusieurs
//    paires de balises (qui elles-même peuvent contenir d'autres balises) à l'exclusion de tout autre contenu.
//
//    On veut transformer cette notation en utilisant des s-expressions plutôt que des balises,
//    c'est-à-dire en écrivant "(xxx...)" plutôt que "<xxx>...</xxx>". Dans notre cas... est soit nul,
//    soit correspond à d'autres balises.
//
//    Exemple:<access><blockcode><a><blockcode><blockcode></blockcode></blockcode></a></blockcode></access>
//    doit être transformé en:
//    (access(blockcode(a(blockcode(blockcode)))))
//    Le programme doit aussi reconnaître un jeu de données mal formé (pour lequel il y a erreur de
//    correspondance entre balises entrantes et fermantes) et écrire à la place du fichier transformé un
//    message d'erreur standardisé de la forme E n </xxx> </yyy> (chaque terme est séparé par un espace)
//    où :- E est la lettre 'E' (qui signale une erreur)
//    - n indique la position en caractères du début de la première balise incorrecte (c'est à dire
//    la position de son caractère <)
//    - </xxx> correspond à la première balise (fermante) incorrecte
//    - </yyy> correspond à la balise (fermante) attendue
//    Format des données
//
//    Entrée
//    Une chaines de caractères contenant une série de balises ouvrantes et fermantes bien ou mal formée.
//
//    Sortie
//    La S-Expression correspondante ou le message d'erreur correspondant.
    fun solve(xmlRaw: String): String {

        var tag = ""
        var sExpression = ""
        var endTag = false
        var openTagsList = mutableListOf<String>()
        var closeTagsList = mutableListOf<String>()

        raw@ for ((index, char) in xmlRaw.withIndex()) {

            when (char) {
                '<' -> {
                    // on rencontre le début d'une balise,
                    // est-ce une balise fermante ou ouvrante
                    endTag = index + 1 < xmlRaw.length && xmlRaw[index + 1] == '/'

                }
                '>' -> {
                    // le caractère est celui qui définit une fin d'une balise
                    // on crée la s expression
                    sExpression += if (!endTag) "($tag" else ")"


//                    if (endTag)
                        closeTagsList.add("$tag")
//                    else openTagsList.add("$tag")

                    tag = ""

                }
                else -> {
                    if (char != '/') tag += char

                }
            }

        }


        val firstTag = closeTagsList.firstOrNull()
        for (tagIndex in 1 until closeTagsList.size) {
            if(firstTag == closeTagsList[tagIndex]){

            }

        }



        return sExpression
    }

    @Test
    fun `case 1`() {
        val output =
            solve("<access><blockcode><a><blockcode><blockcode></blockcode></blockcode></a></blockcode></access>")

        Assertions.assertEquals(
            "(access(blockcode(a(blockcode(blockcode)))))",
            output
        )
    }

    @Test
    fun `case 2`() {
        val output =
            solve("<a><blockcode></blockcode></a><address><blockcode></address><abbr></abbr></address>")

        Assertions.assertEquals(
            "E 50 address blockcode",
            output
        )
    }

    @Test
    fun `case 2 bis`() {
        val output =
            solve("<a><blockcode></blockcode></a><address></blockcode></address><abbr></abbr></address>")

        Assertions.assertEquals(
            "E 50 address blockcode",
            output
        )
    }

    @Test
    fun `case 2 3`() {
        val output =
            solve("<a><blockcode></blockcode></a></address><abbr></abbr></address>")

        Assertions.assertEquals(
            "E 50 address",
            output
        )
    }


    @Test
    fun `case 3`() {
        val output =
            solve("<blockcode><blockcode></blockcode><blockcode></blockcode><abbr><a></a><abbr><a><a><abbr><abbr></abbr></abbr></a></a></abbr></abbr></blockcode>")

        Assertions.assertEquals(
            "(blockcode(blockcode)(blockcode)(abbr(a)(abbr(a(a(abbr(abbr)))))))",
            output
        )
    }
}