package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.lang.IllegalStateException
import java.util.*
import kotlin.math.abs

fun User.toUserView(): UserView {

    val nickName = Utils.transliterations("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit!!.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nickName,
        initials = initials,
        avatar = avatar,
        status = status
    )
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val tempDiff: Long = (date.time/1000 - this.time/1000)*1000
    val diff = abs(tempDiff)
    var result = ""
    result += when {
        diff <= 1 * SECOND -> "только что"
        diff <= 45 * SECOND -> "несколько секунд"
        diff <= 75 * SECOND -> "минуту"
        diff <= 45 * MINUTE -> {
            "${diff / MINUTE} " + if ((diff / MINUTE) in 10..19) "минут" else {
                when ((diff / MINUTE).toInt() % 10) {
                    1 -> "минуту"
                    2, 3, 4 -> "минуты"
                    0, 5, 6, 7, 8, 9 -> "минут"
                    else -> throw IllegalStateException("invalid time")
                }
            }
        }
        diff <= 75 * MINUTE -> "час"
        diff <= 22 * HOUR -> {
            "${diff / HOUR} " + if ((diff / HOUR) in 10..19) "часов" else {
                when ((diff / HOUR).toInt() % 10) {
                    1 -> "час"
                    2, 3, 4 -> "часа"
                    0, 5, 6, 7, 8, 9 -> "часов"
                    else -> throw IllegalStateException("invalid time")
                }
            }
        }
        diff <= 26 * HOUR -> "день"
        diff <= 360 * DAY -> {
            "${diff / DAY} " + if ((diff / DAY) in 10..19) "дней" else {
                when ((diff / DAY).toInt() % 10) {
                    1 -> "день"
                    2, 3, 4 -> "дня"
                    0, 5, 6, 7, 8, 9 -> "дней"
                    else -> throw IllegalStateException("invalid time")
                }
            }
        }
        diff > 360 * DAY -> "более года"
        else -> throw IllegalStateException("invalid time")
    }
    if (!result.contains("только что")) {
        if (tempDiff >= 0) {
            result += " назад"
        } else {
            result = "через $result"
        }
    }
    return result
}
