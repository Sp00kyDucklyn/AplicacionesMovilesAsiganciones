package Practica02

/**
 *  No contiene las siguientes sub cadenas: “bu”, “ba” o “be”
 *  Contiene al menos 3 vocales (es posible repetir las vocales)
 *  Contiene al menos una letra doble, es decir, la misma letra seguida de sí misma.
 * Por ejemplo, la cadena “abba” cumple con esta condición porque se repite b.
 */

/**
 * Practica: Cadena Fina
 * Nombre: Carmen Hernández Echeverría
 * ID 00000240210
 */
fun String.esFina(): Boolean {

        val regex1 = Regex("(\\w)\\1")
        var vocales = arrayOf("a","e","i","o","u")

        var cont = 0

        val condicion1 = regex1.containsMatchIn(this)
        val condicion2 = this.count { it.toString().toLowerCase() in vocales } >= 3
        val condicion3 = !this.contains(Regex("(bu|ba|be)"))

        val condicionesCumplidas = listOf(condicion1, condicion2, condicion3)
        val condicionesVerdaderas = condicionesCumplidas.count { it }

        return condicionesVerdaderas >= 2
//
}

fun main(args: Array<String>) {
    println("bac".esFina())    // Resultado esperado: false
    println("aza".esFina())    // Resultado esperado: false
    println("abaca".esFina())  // Resultado esperado: false
    println("baaa".esFina())   // Resultado esperado: true
    println("aaab".esFina())   // Resultado esperado: true
}



