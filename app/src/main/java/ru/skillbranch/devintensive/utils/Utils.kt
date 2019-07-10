package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.TimeUnits

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


    fun transliteration(payloadIn: String, divider:String = " "): String {
        var retStr:String?
        var payload=payloadIn.replace("\\s+".toRegex()," ")?.trim()
        val russians="абвгдеёжзийклмнопрстуфхцчшщъыьэюя "
        val english=arrayOf("a","b","v","g","d","e","e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u","f","h","c","ch","sh","sh","","i","","e","yu","ya",divider)
        val englishUpper=arrayOf("A","B","V","G","D","E","E","Zh","Z","I","I","K","L","M","N","O","P","R","S","T","U","F","H","C","Ch","Sh","Sh","","I","","E","Yu","Ya",divider.toUpperCase())
        var isUpper:Boolean
        var index:Int
        retStr=""

        for(ch in payload){
            isUpper=ch.isUpperCase()
            index=russians.indexOf(ch.toLowerCase())
            retStr+="${if(index==-1) ch else if(isUpper) englishUpper [index]  else english[index]}"
        }

        return retStr
    }

}
