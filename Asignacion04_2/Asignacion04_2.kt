package Asignacion04_2

fun String?.esVaciaONula():Boolean{
    return this.isNullOrBlank()
}

fun main(args: Array<String>){
    val s1: String? = null
    val s2: String? = ""

    println(s1.esVaciaONula() == true)
    println(s2.esVaciaONula() == true)

    val s3="    "
    println(s3.esVaciaONula() == false)
}