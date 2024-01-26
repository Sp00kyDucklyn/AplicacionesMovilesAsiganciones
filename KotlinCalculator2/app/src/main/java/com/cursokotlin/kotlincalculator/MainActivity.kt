package com.cursokotlin.kotlincalculator

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cursokotlin.kotlincalculator.ui.theme.KotlinCalculatorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var canAddOperation = false
    private var canAddDecimal = true
    private lateinit var workingsTV:TextView
    private lateinit var resultsTV:TextView
    private lateinit var random:Random
    public lateinit var root:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root=findViewById(R.id.root)
        workingsTV=findViewById(R.id.workingsTV)
        resultsTV=findViewById(R.id.resultsTV)
    }

    fun numberAction(view: View) {
        if(view is Button){
            if(view.text == "."){
                if(canAddDecimal){
                    workingsTV.append(view.text)
                    canAddDecimal= false

                }
            }else{
                workingsTV.append(view.text)
            }

            canAddOperation = true
        }
    }
    fun operationAction(view: View) {
        if(view is Button && canAddOperation){
            workingsTV.append(view.text)
            canAddOperation = false
            canAddDecimal= true
        }
    }

    fun equalsAction(view: View) {
        resultsTV.text = calculateResults()
    }

    private fun calculateResults():String{
        val digitsOperators = digitsOperators()
        if(digitsOperators.isEmpty()){
            return ""
        }
        val timesDivision = timesDivisionCalculate(digitsOperators)
        if(timesDivision.isEmpty()){
            return ""
        }

        val result = addSubstractCalculate(timesDivision)

        return result.toString()
    }

    private fun addSubstractCalculate(passedList: MutableList<Any>):Float{

       var result = passedList[0] as Float
        for(i in passedList.indices){
            if(passedList[i] is Char && i != passedList.lastIndex){
                val operator = passedList[i]
                val nextDigit = passedList[i+1] as Float
                if(operator == '+'){
                    result += nextDigit
                }
                if(operator == '-'){
                    result -= nextDigit
                }
            }
        }


        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>):MutableList<Any>{
        var list = passedList
        while(list.contains('x') || list.contains('/')){
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>):MutableList<Any>{
        val newList = mutableListOf<Any>()

        var restartIndex = passedList.size
        for(i in passedList.indices){
            if(passedList[i] is Char && i != passedList.lastIndex && i< restartIndex){
                val operator = passedList[i]
                val prevDigit = passedList[i-1] as Float
                val nextDigit = passedList[i+1] as Float

                when (operator){
                    'x' ->{
                        newList.add(prevDigit*nextDigit)
                        restartIndex = i+1
                    }
                    '/' ->{
                        newList.add(prevDigit/nextDigit)
                        restartIndex= i+1
                    }
                    else ->{
                        newList.add(prevDigit)
                        newList.add(operator)
                    }

                }

            }
            if(i >restartIndex){
                newList.add(passedList[i])
            }
        }

        return newList
    }

    private fun digitsOperators():MutableList<Any>{
        var list= mutableListOf<Any>()
        var currentDigit=""
        for(character in workingsTV.text){
            if(character.isDigit()|| character=='.'){
                currentDigit += character
            }else{
                list.add(currentDigit.toFloat())
                currentDigit=""
                list.add(character)
            }

        }

        if(currentDigit != ""){
            list.add(currentDigit.toFloat())
        }

        return list
    }

    fun allClearAction(view: View) {
        workingsTV.text = ""
        resultsTV.text=""
    }
    fun cambiarColor(view: View) {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()

        var color:Int = Color.rgb(red, green, blue);
        root.setBackgroundColor(color)
    }
    fun backSpaceAction(view: View) {
        val length = workingsTV.length()
        if(length > 0){
            workingsTV.text = workingsTV.text.subSequence(0,length -1)
        }

    }




}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinCalculatorTheme {
        Greeting("Android")
    }
}

