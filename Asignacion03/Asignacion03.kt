fun verificaCadena(cadena:String): Boolean {
    return if(!cadena.isEmpty()){
        if(cadena[0].isLetter() || cadena[0].equals('_')) {
            cadena.all { caracter -> caracter.isLetterOrDigit() || caracter == '_' }
        }else{
            false
        }

    }else{
        false
    }
}

fun main(args: Array<String>){
    println(verificaCadena("nombre"))
    println(verificaCadena("_nombre")) // true
    println(verificaCadena("_12")) // true
    println(verificaCadena("")) // false
    println(verificaCadena("012")) // false
    println(verificaCadena("no$"))
}