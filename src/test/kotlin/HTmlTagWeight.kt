import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HTmlTagWeight {
//    Dans l'exemple précédent : a et e ont une profondeur de 1,
//    b et c ont une profondeur de 2
//    et d a une profondeur de 3.
//
//    On définit enfin le poids d'un nom de balise par la somme des inverses des profondeurs de chacune de ses occurrences.
//
//    Par exemple, dans la chaine a-abab-b-a-b, il y a : - deux balises a de profondeur 1 et 2
//    - deux balises b de profondeurs 1 et 3.
//    Le poids de a est donc de (1/1)+(1/2) = 1.5 et le poids de b est donc (1/1)+(1/3)=1.33.
//
//    Dans ce challenge vous devez déterminer la lettre correspondant à la balise de plus grand poids de la chaîne passée en paramètre.
//
//    Format des données
//
//    Entrée
//    Sur une seule ligne, une chaîne correctement formée d'au maximum 1024 caractères représentant une imbrication de balises.
//
//    Sortie
//    La lettre correspondant au nom de balise de plus grand poids. Si deux noms de balises ont le même poids, affichez le plus petit dans l'ordre alphabétique.
    fun solve(tags: String): Char {

        var currentTags = mutableListOf<Char>()
        var tagsAndWeight = mutableMapOf<Char, MutableList<Int>>()

        var skip = false
        loop@for ((index, tag) in tags.withIndex()) {
            if (skip) {
                skip = false
                continue@loop
            }
            if ('-' == tag) {
                currentTags.remove(tags[index + 1])
                val weight = currentTags.size + 1

                var listWeight = mutableListOf<Int>()
                if (tagsAndWeight[tags[index + 1]] == null) {
                    tagsAndWeight.put(tags[index + 1], listWeight)
                }
                else {
                    listWeight = tagsAndWeight[tags[index + 1]]!!
                }
                listWeight.add(weight)

                skip = true

            } else {
                currentTags.add(tag)
            }
        }

        var computedWeight = mutableMapOf<Double, MutableList<Char>>()
        for ((key,value ) in tagsAndWeight) {
            val computedWeigh = value.map{it.toDouble()}.map {
                (1.0)/(it)
            }.sum()
                //.map { (1.0)/(it)  }.sum()

            var tagList = mutableListOf<Char>()
            if(computedWeight.get(computedWeigh) == null)  computedWeight.put(computedWeigh, tagList)
            else tagList = computedWeight.get(computedWeigh)!!
            tagList.add(key)
        }

        var max = computedWeight.keys.max()
        var tagList = computedWeight.get(max)
        tagList!!.sort()
        return tagList!!.get(0)

    }

    @Test
    fun `must be p`() {
        var tag = solve("wl-l-wpy-y-p")
        Assertions.assertEquals('p', tag)
    }

    @Test
    fun `must be x`() {
        var tag = solve("zx-x-zxyz-z-y-x")
        Assertions.assertEquals('x', tag)
    }

    @Test
    fun `must be a`() {
        var tag = solve("bc-c-ba-a")
        Assertions.assertEquals('a', tag)
    }
}