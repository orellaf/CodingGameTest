import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class QuicksandTest {

//    L'objet de ce challenge est de déterminer la profondeur maximale
//    des zones de sable mouvant d'un désert. Le désert est un rectangle composé
//    de cases de terre ferme ou de sable mouvant. La profondeur d'une case de sable
//    mouvant est le nombre minimal de déplacements horizontaux et/ou verticaux à effectuer
//    pour accéder à une case de terre ferme.
//
//    ........
//    ..11111.
//    .122221.
//    .111111.
//    ........

    //    case rouge ligne 3, colonne 3
//
//    Dans l'exemple ci-dessus les cases de terre ferme sont représentées par des . et
//    les chiffres représentent la profondeur des cases de sable mouvant. La case en rouge
//    a une profondeur de 2 car il faut faire au moins deux déplacements horizontaux et/ou verticaux
//    (par exemple, un vers le haut et un vers la gauche, ou bien 2 vers la gauche) pour atteindre la terre ferme.
//
//    La terre ferme n'est pas nécessairement connexe, il peut y avoir des îles de terre
//    ferme au milieu des sables mouvants. Par ailleurs, sur tout le contour de la carte
//    rectangulaire, il n'y a que de la terre ferme.
//
//    Format des données
//
//    Entrée
//    Ligne 1 : deux entiers H et L compris entre 3 et 40 séparés par un espace,
//    indiquant respectivement la hauteur et la largeur de la carte.
//    Lignes 2 à H+1 : les lignes de la carte représentées par des chaînes de L caractères.
//    Les caractères de la ligne sont soit . (terre ferme) soit # (sable mouvant).
//
//    Sortie
//    Un entier représentant la profondeur maximale des zones de sable mouvant du désert.
    fun solve(nbRows: Int, nbColumns: Int, lines: List<String>): Int {
        var fieldMap = mutableMapOf<Pair<Int, Int>, Char>()
        val sand = '#'
        val ground = '.'

        // build the map
        for ((row, line) in lines.withIndex()) {
            for ((column, case) in line.withIndex()) {
                fieldMap.put(row to column, case);
            }
        }

        // find sand cases
        var caseAndNbMovement = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
        for ((casePosition, caseType) in fieldMap) {
            if (sand.equals(caseType)) {
                val row = casePosition.first
                val col = casePosition.second


                var list = mutableListOf<Int>()
                var index = 1
                while (list.isEmpty()) {
                    for (r in row - index until row + index) {
                        for (c in col - index until col + index) {
                            if (c == col && row == r) continue
                            val otherCase = fieldMap.get(r to c)
                            if (ground.equals(otherCase)) {
                                val nbMovement = Math.abs(r - row) + Math.abs(c - col)
                                list.add(nbMovement)
                            }
                        }
                    }
                    index++
                }


                caseAndNbMovement.put(casePosition, list)

            }

        }

        println("Movement details")
        var nbMovement = 0;
        for ((casePosition, value) in caseAndNbMovement) {
            val movementByCase = value.min()
            nbMovement = Math.max(nbMovement, movementByCase!!)

            println("row= ${casePosition.first}, column= ${casePosition.second} => nbMovement= $nbMovement")
        }
        return nbMovement

    }

    @Test
    fun `nbMovemnent = 1`() {
        val nbMovemnent = solve(
            nbColumns = 5,
            nbRows = 3,
            lines = arrayListOf(
                ".....",
                ".###.",
                "....."
            )
        )
        Assertions.assertEquals(1, nbMovemnent)
    }


    @Test
    fun `nbMovemnent = 2`() {
        val nbMovemnent = solve(
            nbColumns = 10,
            nbRows = 8,
            lines = arrayListOf(
                "..........",
                ".#####.##.",
                ".########.",
                ".########.",
                ".###.#.#..",
                ".###.####.",
                ".########.",
                ".........."
            )
        )
        Assertions.assertEquals(2, nbMovemnent)
    }


}