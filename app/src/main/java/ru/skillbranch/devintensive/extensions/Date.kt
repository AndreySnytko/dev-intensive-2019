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
        (compare in 76..2700) -> "${if(diff<0)    "${TimeUnits.MINUTE.plural(compare/60)} назад" else "через ${TimeUnits.MINUTE.plural(compare/60)}"}" //Минуты
        (compare in 2701..4500) -> "${if(diff<0)    "час назад" else "через час"}" //45мин - 75мин
        (compare in 4501..79200) -> "${if(diff<0)    "${TimeUnits.HOUR.plural(compare/60/60)} назад" else "через ${TimeUnits.HOUR.plural(compare/60/60)}"}" //75мин - 22часа Часов
        (compare in 79200..93600) -> "${if(diff<0)    "день назад" else "через день"}" //22часа - 26 часов
        (compare in 93601..31104000) -> "${if(diff<0)    "${TimeUnits.DAY.plural(compare/60/60/24)} назад" else "через ${TimeUnits.DAY.plural(compare/60/60/24)}"}" //22часа - 26 часов/ Дней
        (compare > 31104000) -> "${if(diff<0)    "более года назад" else "более чем через год"}" //22часа - 26 часов)
        else -> "дата не известна"
    }

    return "$ret"
}

fun TimeUnits.plural(fullTime : Long):String{
    var human:String=""
    val timeType=this
    val IntArray: Array<Char> = arrayOf('0','1','2','3','4','5','6','7','8','9')
    val index=fullTime.toString().length-1
    val strTime:Char=fullTime.toString()[index]
    val time: Int = IntArray.indexOf(strTime)

    when(timeType){
        TimeUnits.SECOND -> human = "${if((time==1)&&(fullTime!=11.toLong())) fullTime.toString() + " секунду" else if((time in 2..4)&& !(fullTime in 12..20)) fullTime.toString() + " секунды" else fullTime.toString() + " секунд"}"    //"1 секунду" "2,3,4 секунды" "5,6,7,8,9 секунд"
        TimeUnits.MINUTE -> human = "${if((time==1)&&(fullTime!=11.toLong())) fullTime.toString() + " минуту"  else if((time in 2..4)&& !(fullTime in 12..20)) fullTime.toString() + " минуты"  else fullTime.toString() + " минут"}"//"1 минутe" "234 минуты" "5,6,7,8,9 минут"
        TimeUnits.HOUR -> human = "${if((time==1)&&(fullTime!=11.toLong())) fullTime.toString() + " час"       else if((time in 2..4)&& !(fullTime in 12..20)) fullTime.toString() + " часа"    else fullTime.toString() + " часов"}"//"1 час" 234 часа 59 часов
        TimeUnits.DAY -> human = "${if((time==1)&&(fullTime!=11.toLong())) fullTime.toString() + " день"       else if((time in 2..4)&& !(fullTime in 12..20)) fullTime.toString() + " дня"     else fullTime.toString() + " дней"}"//"1 день" 234 дня 59 дней
    }


    return human
}


enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
