import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RIBTest {

    //    Enoncé
//
//    Dans ce challenge, vous devez déterminer si un RIB est valide. Un RIB est composé de :
//    - 5 caractères pour le code banque,
//    - 5 caractères pour le code guichet,
//    - 11 caractères pour le numéro de compte,
//    - 2 chiffres pour la clé RIB

//    Les caractères peuvent être soit :
//    - des chiffres entre 0 et 9
//    - des lettres non accentuées entre a et z ou A et Z

//    La clé RIB permet de détecter des erreurs dans la transcription du RIB. Sa valeur est calculée comme suit :
//    - chaque lettre est remplacée par le chiffre correspondant (même chiffre pour les majuscules que pour les minuscules) :
//    a, j => 1
//    b, k, s => 2
//    c, l, t => 3
//    d, m, u => 4
//    e, n, v => 5
//    f, o, w => 6
//    g, p, x => 7
//    h, q, y => 8
//    i, r, z => 9

    //    - On calcule la somme de : 89 × codedebanque + 15 × codeguichet + 3 × numérodecompte
//    - La clé RIB est alors égale à 97 moins le modulo 97 de la somme précédente.
//    Votre programme va recevoir une série de RIB et va déterminer s'ils sont valides.
//
//    Données
//
//    Entrée
//    Une série de lignes contenant des données au format RIB, le code banque, le code guichet, le numéro de compte et
//    la clef RIB sont séparés par des espaces.
//
//    Sortie
//    Une série de lignes contenant OK ou KO indiquant si la ligne correspondante du fichier d'entrée
//    correspond à un RIB valide.
    fun checkRIB(ribs: Array<String>): Array<String> {
        val ribsResult = mutableListOf<String>()
        for (rib in ribs) {
            val data = rib.split(" ")
            val bankCode = data[0]
            val branchCode = data[1]
            val accountNumber = data[2]
            val actualKey = data[3]

            val value = 89 * computeValue(bankCode) + 15 * computeValue(branchCode) + 3 * computeValue(accountNumber)

            val computedKey = 97 - value % 97
            ribsResult.add(if (computedKey == actualKey.toLong()) "OK" else "KO")

        }

        return ribsResult.toTypedArray()
    }

    fun computeValue(data: String): Long {
        var count = ""
        for (char in data) {
            count += when (val c = char.toLowerCase()) {
                'a', 'j' -> "1"
                'b', 'k', 's' -> "2"
                'c', 'l', 't' -> "3"
                'd', 'm', 'u' -> "4"
                'e', 'n', 'v' -> "5"
                'f', 'o', 'w' -> "6"
                'g', 'p', 'x' -> "7"
                'h', 'q', 'y' -> "8"
                'i', 'r', 'z' -> "9"
                else -> c.toString()
            }
        }
        return count.toLong()
    }

    @Test
    fun `case 1`() {

        val result = checkRIB(
            arrayOf(
                "blHhf sE6xP ReIXBOX7ih 88",
                "at4Mu kCZpS nKzy4Ro0nk 83",
                "KnJj7 sfyKs 5cuHLFsWcv 06",
                "Gh8KF nU5mB wATlslF2WP 18",
                "0B7T9 L0ce4 ZI3LXlNJev 44",
                "1wTq8 74Idw uqby2hLWpW 45",
                "dbSZY gnIUk 1QK0OS7w5r 72",
                "9FNLi 9dAeH ngK4LUZNv3 69",
                "TmlJk eSSv6 SACtoCUpnC 46",
                "fMxmX SuHxs 8r1j6R2mAR 59",
                "CiMlg 2iAKZ fqvC583Qmy 20",
                "V0Pal 3zcgv M7u8nT8IYs 22",
                "38f12 vsB7n xb8Du2fLuz 16",
                "t8qWE pzOSO EQx4RpyziX 33"
            )
        )

        Assertions.assertArrayEquals(
            arrayOf(
                "OK",
                "OK",
                "KO",
                "OK",
                "KO",
                "KO",
                "KO",
                "OK",
                "KO",
                "OK",
                "KO",
                "OK",
                "OK",
                "KO"
            ),
            result
        )

    }

    @Test
    fun `case 2`() {

        val result = checkRIB(
            arrayOf(
                "N2DRS myvFr Biu88hPIkM 26",
                "sCrIb AeZCc zG9tam9G6r 16",
                "t9qrg wqCTt 7a7TG8iJWW 48",
                "pMS9E SF9g0 rgz7MSohJE 33",
                "jAk58 HSMp2 22T25HU446 55",
                "BX2q3 o06Su 62e4OfFkuU 39",
                "q8y0T axZbW NQF9Tx0A6V 56",
                "nn17r YgASH qQwzHSDJ9i 66",
                "QwjHy uHz3F KmTGFQ3FCO 45",
                "i2HcR eRR9m GmarH7mj16 62",
                "qZDbS YZoF0 YUY1Po5Fih 26"
            )
        )

        Assertions.assertArrayEquals(
            arrayOf(
                "OK",
                "OK",
                "OK",
                "OK",
                "KO",
                "OK",
                "OK",
                "OK",
                "OK",
                "OK",
                "KO"
            ),
            result
        )

    }
}