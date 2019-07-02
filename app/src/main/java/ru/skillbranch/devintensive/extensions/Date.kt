package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    //extension функция генерить статическую функцию, к.т. можно применить к экземпляру класса
    val dateFormat = SimpleDateFormat(pattern,Locale("ru"))
    return dateFormat.format(this) //будет возвращать строку в к.т. есть представление о том как уже отформатирован наш объект
}


//ещё одна extension функция к.т. к объекту даты будет добавлять сдвиг
fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECOND):Date{ //Refactor - Change Signature
    var time = this.time //это в милисекундах

    time += when(units){
        TimeUnits.SECOND -> value * SECOND //"second","seconds" -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
//        else -> throw IllegalStateException("invalid unit")
    }
    this.time=time
    return  this
}


fun Date.humanizeDiff(date: Date=Date()): String {
//    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    //Домашнее задание
    return "TODO"
}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
