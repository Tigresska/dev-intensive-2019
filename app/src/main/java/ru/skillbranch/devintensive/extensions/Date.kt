package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

/**
 * Created by tigresska on 2019-06-29
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this


}

/*

Реализуй extension Date.humanizeDiff(date) (значение по умолчанию текущий момент времени) для форматирования вывода
разницы между датами в человекообразном формате, с учетом склонения числительных. Временные интервалы преобразований к
человекообразному формату доступны в ресурсах к заданию
Пример:
Date().add(-2, TimeUnits.HOUR).humanizeDiff() //2 часа назад
Date().add(-5, TimeUnits.DAY).humanizeDiff() //5 дней назад
Date().add(2, TimeUnits.MINUTE).humanizeDiff() //через 2 минуты
Date().add(7, TimeUnits.DAY).humanizeDiff() //через 7 дней
Date().add(-400, TimeUnits.DAY).humanizeDiff() //более года назад
Date().add(400, TimeUnits.DAY).humanizeDiff() //более чем через год

0с - 1с "только что"

1с - 45с "несколько секунд назад"

45с - 75с "минуту назад"

75с - 45мин "N минут назад"

45мин - 75мин "час назад"

75мин 22ч "N часов назад"

22ч - 26ч "день назад"

26ч - 360д "N дней назад"

>360д "более года назад"
*/
val minutS = listOf<Long>(2, 3, 4, 22, 23, 24, 32, 33, 34) //минуты
val minutU = listOf<Long>(21, 31, 41) //минуту
val hourA = listOf<Long>(2, 3, 4, 22) //часа
val hourOv = listOf<Long>(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20) //часов


fun Date.humanizeDiff(date: Date = Date()): String {

    var diff: Long = Math.round((this.time - date.time) / 1000.0)
    var timeUnitdiff = TimeUnits.SECOND

    if (timeUnitdiff == TimeUnits.SECOND && diff.absoluteValue > 59) {

        timeUnitdiff = TimeUnits.MINUTE
        diff /= 60
    }
    if (timeUnitdiff == TimeUnits.MINUTE && diff.absoluteValue > 59) {

        timeUnitdiff = TimeUnits.HOUR
        diff /= 60
    }
    if (timeUnitdiff == TimeUnits.HOUR && diff.absoluteValue > 23) {

        timeUnitdiff = TimeUnits.DAY
        diff /= 24
    }

    val diffAbs = diff.absoluteValue

    return when (timeUnitdiff) {

        TimeUnits.SECOND -> when (diff) {
            in -1..1 -> "только что"
            in 2..45 -> "через несколько секунд"
            in -45..-2 -> "несколько секунд назад"
            in 46..60 -> "через минуту"
            in -60..-46 -> "минуту назад"
            else -> " "

        }
        TimeUnits.MINUTE -> when {
            diff == -1L -> "минуту назад"
            diff == 1L -> "через минуту"
            minutS.contains(diffAbs) -> when (diff < 0) {
                true -> "$diffAbs минуты назад"
                false -> "через $diffAbs минуты"
            }
            minutU.contains(diffAbs) -> when (diff < 0) {
                true -> "$diffAbs минуту назад"
                false -> "через $diffAbs минуту"
            }

            diff in -60..-45 -> "час назад"
            diff in 45..60 -> "через час"
            diff in -45..-5 -> "$diffAbs минут назад"
            diff in 5..45 -> "через $diffAbs минут"

            else -> " "

        }
        TimeUnits.HOUR -> when {
            diff == -1L -> "час назад"
            diff == 1L -> "через час"
            diff == -21L -> "21 час назад"
            diff == 21L -> "через 21 час"
            hourA.contains(diffAbs) -> when (diff < 0) {
                true -> "$diffAbs часа назад"
                false -> "через $diffAbs часа"
            }
            hourOv.contains(diffAbs) -> when (diff < 0) {
                true -> "$diffAbs часов назад"
                false -> "через $diffAbs часов"
            }
            diff == -23L -> "день назад"
            diff == 23L -> "через день"

            else -> " "

        }
        TimeUnits.DAY -> when {
            diff == -1L -> "день назад"
            diff == 1L -> "через день"
            diff < -360 -> "более года назад"
            diff > 360 -> "более чем через год"
            diffAbs % 10 in 5..20 || diffAbs % 100 in (5..20)
                    || diffAbs % 10 == 0L || diffAbs % 100 == 0L -> when (diff < 0) {
                true -> "$diffAbs дней назад"
                false -> "через $diffAbs дней"
            }
            diffAbs % 10 == 1L || diffAbs % 100 == 1L -> when (diff < 0) {
                true -> "$diffAbs день назад"
                false -> "через $diffAbs день"
            }
            diffAbs % 10 in 2..4 || diffAbs % 100 in (2..4) -> when (diff < 0) {
                true -> "$diffAbs дня назад"
                false -> "через $diffAbs дня"
            }
            else -> " "

        }


    }


}

