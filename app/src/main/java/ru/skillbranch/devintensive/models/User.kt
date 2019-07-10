package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User ( //преобразовали класс в data class и тогда в нём появляется метод toString, к.т. выводит всё содержимое нашего класса
                  //print(user3)
                 // User(id=2, firstName=John, lastName=Silverhand, avatar=null, rating=0, respect=0, lastVisit=null, isOnline=false)
                 // выводятся только те, к.т. объявлены в первичном конструкторе
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit: Date? = null,
    val isOnline:Boolean = false

//    var introBit :String="$firstName $lastName"
    ){

    var introBit :String
    //вторичный конструктор
    constructor(id:String,firstName:String?,lastName:String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

//    var introBit :String=getIntro()
//  constructor(id:String) : this(id, "John", "Doe $id")
    constructor(id:String) : this(id, "John", "Doe")

    init {
        introBit = getIntro()

        println("It's Alive!!! \n " +
                "${if(lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"} \n" +
                "${getIntro()}")
    }

    companion object Factory{ //не всегда удобно создавать объекты через конструктор, для этого используется фабрика
        private var lastId : Int = -1
        fun makeUser(fullName : String?) : User{
            lastId++

            //Деструктурирование объектов, получаем доступ к
            //Pair - это дата класс, DTO  про к.т. говорили в лекции
            val (firstName,lastName)= Utils.parseFullName(fullName)

            return User(id="$lastId", firstName=firstName, lastName=lastName)
        }
    }

    data class Builder(
        var id: String = "0",
        var firstName: String? = null,
        var lastName: String? = null,
        var avatar: String? = null,
        var rating: Int = 0,
        var respect: Int = 0,
        var lastVisit: Date? = Date(),
        var isOnline: Boolean = false
    ) {
        fun id(value: String) = apply { id = value }
        fun firstName(value: String?) = apply { firstName = value }
        fun lastName(value: String?) = apply { lastName = value }
        fun avatar(value: String?) = apply { avatar = value }
        fun rating(value: Int) = apply { rating = value }
        fun respect(value: Int) = apply { respect = value }
        fun lastVisit(value: Date?) = apply { lastVisit = value }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }

    private fun getIntro()="""
        getIntro
    """.trimIndent()

    fun printMe()= // Unit - функции к.т. не возвращают никакого результата, аналог void
        println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent())


}

