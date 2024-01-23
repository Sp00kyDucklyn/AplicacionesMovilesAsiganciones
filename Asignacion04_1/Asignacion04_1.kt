package Asignacion04_1

fun List<Int>.suma():Int{
    var result = 0
        for(i in this){
        result +=i
    }
    return result
}

fun main(args:Array<String>){
    val sum = listOf(1,2,3)
    println(sum.suma()) //6
}