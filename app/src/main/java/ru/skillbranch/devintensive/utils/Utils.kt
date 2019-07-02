package ru.skillbranch.devintensive.utils

/**
 * Created by tigresska on 2019-06-29
 */

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return when {
            firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null to null
            firstName.isNullOrBlank() -> null to lastName
            firstName.isNullOrBlank() -> null to lastName
            else -> firstName to lastName
        }
    }

    /*
    Реализуй метод Utils.toInitials(firstName lastName) принимающий в качестве аргументов имя и фамилию пользователя
    (null, пустую строку) и возвращающий строку с первыми буквами имени и фамилии в верхнем регистре
    (если один из аргументов null то вернуть один инициал, если оба аргумента null вернуть null)
    Пример:
    Utils.toInitials("john" ,"doe") //JD
    Utils.toInitials("John", null) //J
    Utils.toInitials(null, null) //null
    Utils.toInitials(" ", "") //null
    */
    fun toInitials(firstName: String?, lastName: String?): String? {

        return when {
            firstName.isNullOrBlank() && lastName.isNullOrBlank() -> null
            firstName.isNullOrBlank() -> lastName?.toUpperCase()?.get(0).toString()
            lastName.isNullOrBlank() -> firstName.toUpperCase().get(0).toString()
            else -> firstName.toUpperCase().get(0).toString() + lastName.toUpperCase().get(0).toString()
        }

    }


    /*
    Реализуй метод Utils.transliteration(payload divider) принимающий в качестве аргумента строку
    (divider по умолчанию " ") и возвращающий преобразованную строку из латинских символов, словарь символов
    соответствия алфовитов доступен в ресурсах к заданию
    Пример:
    Utils.transliteration("Женя Стереотипов") //Zhenya Stereotipov
    Utils.transliteration("Amazing Петр","_") //Amazing_Petr
    */
    fun transliteration(payload: String, divider: String = " "): String {
        val translitMap = hashMapOf(
            "а" to "a", "б" to "b", "в" to "v", "г" to "g", "д" to "d", "е" to "e",
            "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i", "й" to "i", "к" to "k",
            "л" to "l", "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r",
            "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h", "ц" to "c",
            "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "",
            "э" to "e", "ю" to "yu", "я" to "ya",
            "А" to "A", "Б" to "B", "В" to "V", "Г" to "G", "Д" to "D", "Е" to "E",
            "Ё" to "E", "Ж" to "Zh", "З" to "Z", "И" to "I", "Й" to "I", "К" to "K",
            "Л" to "L", "М" to "M", "Н" to "N", "О" to "O", "П" to "P", "Р" to "R",
            "С" to "S", "Т" to "T", "У" to "U", "Ф" to "F", "Х" to "H", "Ц" to "C",
            "Ч" to "Ch", "Ш" to "SH", "Щ" to "Sh'", "Ъ" to "", "Ы" to "I", "Ь" to "",
            "Э" to "E", "Ю" to "YU", "Я" to "Ya"
        )

        var result = ""

        payload.replace(Regex("\\s"), divider).toCharArray().forEach {
            val temp = it.toString()
            result += when (translitMap.containsKey(temp)) {
                true -> translitMap.getValue(temp)
                false -> temp
            }
        }


        return result

    }


}