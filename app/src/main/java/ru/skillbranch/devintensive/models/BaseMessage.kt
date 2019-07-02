package ru.skillbranch.devintensive.models

import android.service.voice.AlwaysOnHotwordDetector
import java.util.*

abstract class BaseMessage(
    val id:String,
    val from:User?,//пользователь к.т. отправил данное сообщение
    val chat:Chat,
    val isIncoming: Boolean = false,
    val date:Date= Date()

) {

    abstract fun formatMessage():String

    companion object AbstractFactory{
        var lastId=-1;
        fun makeMessage(from:User?, chat:Chat, date:Date=Date(),type:String="text", payload: Any?):BaseMessage{ // Any - аналог Object d в Java, но меньше методов
            lastId++
            return when(type){
                "image"->ImageMessage("$lastId", from , chat, date = date, image = payload as String)
                    else -> TextMessage("$lastId", from , chat, date = date, text = payload as String)

            }

        }
    }
}