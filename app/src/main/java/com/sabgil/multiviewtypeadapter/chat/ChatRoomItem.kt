package com.sabgil.multiviewtypeadapter.chat

import com.sabgil.mutiviewtype.BaseItem
import java.util.*

sealed class ChatRoomItem(key: Any) : BaseItem(key) {

    data class MyWords(val key: String, val content: String) : ChatRoomItem(key)

    data class OpponentWords(val key: String, val content: String) : ChatRoomItem(key)

    data class DateSection(val date: String) : ChatRoomItem(date)
}