package ru.skillbranch.devintensive.models

/**
 * Created by tigresska on 2019-06-28
 */

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(

    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false

) {
//    var introBit :String

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
//        introBit = getIntro()
        println(
            "It's Alive!!! \n" +
                    "${if (lastName == "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n"
        )
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parceFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }


    /*               "${getIntro()}")
       }

       private fun getIntro() = """
           tu tu ru tuuuuu !!!
           tu tu ru tuuuuu !!!

           tu tu ru tuuuuuuuuu ...
           tu tu ru tuuuuuuuuu ...
           ${"\n\n\n"}
           $firstName $lastName


       """.trimIndent()

       fun printMe() = println("""
           id: $id
           firstName: $firstName
           lastName: $lastName
           avatar: $avatar
           rating: $rating
           respect: $respect
           lastVisit: $lastVisit
           isOnline: $isOnline

       """.trimIndent())*/
}