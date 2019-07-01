package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humnizeDiff
import java.util.*

/**
 * Created by tigresska on 2019-07-01
 */
class ImageMessage (
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?

) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "id: $id ${from?.firstName}" +
            " ${if (isIncoming) "получил" else "отправил"} изображение \"$image\" ${date.humnizeDiff()}"
}