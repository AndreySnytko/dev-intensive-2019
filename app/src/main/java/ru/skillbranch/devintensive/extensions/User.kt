package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

//Не надо писать this, идентификатор автоматически замапился на конструктор UserView
fun User.toUserView() : UserView {

    val nickName = Utils.transliteration("$firstName $lastVisit")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if(lastVisit == null) "Ещё ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}" // Перенесли реализацию в Date.kt , Alt+enter - сделали публичным методом

    return UserView(
        id,
        fullName = "$firstName $lastName", //так как это дата класс, он позволяет напрямую мапить эти данные
        nickName = nickName,
        initials = initials,
        avatar = avatar, //это прямая ссылка на дата класс User
        status = status
    )

}

