package ru.skillbranch.devintensive.utils

//Создан как новый Kotlin file/class Object
// они что то вроде сингл тонов, статических методов функций
object Utils {
    fun parseFullName(fullName : String?):Pair<String?,String?>{
        //TODO: исправить метод чтобы корректно отрабатывал null, пробел, 2 пробела
        val parts : List<String>? = fullName?.split(" ") //т.к. полное имя может быть null, то вызываем безопасный метод через знак ?
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

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