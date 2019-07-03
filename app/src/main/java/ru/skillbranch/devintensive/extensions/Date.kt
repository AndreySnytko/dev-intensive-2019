package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


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

    var firstDate = this//дата к к.т. применяем метод humanizeDiff
    var secondDate= date
    var diff=(firstDate.time-secondDate.time)/1000
    var ret:String

    //уже обсуждалось тут, что левая часть не включительно, правая включительно
    //assertEquals("только что", Date().add(-1, TimeUnits.SECOND).humanizeDiff(Date()))

    val compare= abs(diff)
    ret = when {
        (compare in 0..1)-> "только что"
        (compare in 2..45) -> "${if(diff<0) "несколько секунд назад" else "через несколько секунд"}"
        (compare in 46..75) -> "${if(diff<0) "минуту назад" else "через минуту"}"
        (compare in 76..2700) -> "${if(diff<0)    "${compare/60} минуты назад" else "через ${compare/60} минуты"}"
        (compare in 2701..4500) -> "${if(diff<0)    "час назад" else "через час"}" //45мин - 75мин
        (compare in 4501..79200) -> "${if(diff<0)    "${compare/60/60} часа назад" else "через ${compare/60/60} часа"}" //75мин - 22часа
        (compare in 79200..93600) -> "${if(diff<0)    "день назад" else "через день"}" //22часа - 26 часов
        (compare in 93601..31104000) -> "${if(diff<0)    "${compare/60/60/24} дней назад" else "через ${compare/60/60/24} дней"}" //22часа - 26 часов
        (compare > 31104000) -> "${if(diff<0)    "более года назад" else "более чем через год"}" //22часа - 26 часов)
        else -> "дата не известна"
    }

    return "$ret"
}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
