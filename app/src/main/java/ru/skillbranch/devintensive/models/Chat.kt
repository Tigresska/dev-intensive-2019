package ru.skillbranch.devintensive.models

/**
 * Created by tigresska on 2019-07-01
 */
class Chat (
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val message: MutableList<BaseMessage> = mutableListOf()
) {
}