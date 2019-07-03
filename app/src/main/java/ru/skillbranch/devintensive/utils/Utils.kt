package ru.skillbranch.devintensive.utils

//Создан как новый Kotlin file/class Object
// они что то вроде сингл тонов, статических методов функций
object Utils {
    fun parseFullName(fullName : String?):Pair<String?,String?>{
        //TODO: исправить метод чтобы корректно отрабатывал null, пробел, 2 пробела



        //Удаляем лишние пробелы "  John   Doe    ", replace - удаляет двойные, trim - удаляет с начала и с конца
        val parts : List<String>? = fullName?.replace("\\s+".toRegex()," ")?.trim()?.split(" ")
//        val parts : List<String>? = fullName?.split(" ") //т.к. полное имя может быть null, то вызываем безопасный метод через знак ?
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)


        //TODO: Не очень красивое решение, переделать
        if((firstName=="")||(firstName==" "))firstName=null
        if((lastName=="")||(lastName==" "))lastName=null

        //return Pair(firstName,lastName) //Встроенный дата класс, в котлине их 2а (Pair,Triple)
        // либо можно
        return firstName to lastName //это пара ключ-значение
    }

    fun translitiration(payload: String, divider:String = " "): String {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return "TODO"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return "TODO"
    }
}