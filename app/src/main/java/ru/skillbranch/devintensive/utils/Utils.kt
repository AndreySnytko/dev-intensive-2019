package ru.skillbranch.devintensive.utils

//Создан как новый Kotlin file/class Object
// они что то вроде сингл тонов, статических методов функций
object Utils {
    fun parseFullName(fullName : String?):Pair<String?,String?>{

        //Удаляем лишние пробелы "  John   Doe    ", replace - удаляет двойные, trim - удаляет с начала и с конца
        val parts : List<String>? = fullName?.replace("\\s+".toRegex()," ")?.trim()?.split(" ")
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
        var fN=firstName?.replace("\\s+".toRegex(),"")?.trim()
        var lN=lastName?.replace("\\s+".toRegex(),"")?.trim()
        var initials:String?
        if((fN!="")&&(fN!=null))fN=fN.substring(0,1).toUpperCase()
        if((lN!="")&&(lN!=null))lN=lN.substring(0,1).toUpperCase()


        if(((fN=="")||(fN==null))&&((lN=="")||(lN==null))){
            initials=null
        }else{
            initials = "${if(fN==null) "" else fN}${if(lN==null) "" else lN}"
        }

        return initials
    }
}