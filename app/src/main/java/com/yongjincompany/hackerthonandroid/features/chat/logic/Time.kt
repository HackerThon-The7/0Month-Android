package com.yongjincompany.hackerthonandroid.features.chat.logic

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun timeStamp(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat("현재 시간은 HH:mm\n 난 밥 먹을시간이다~")
        val time = sdf.format(Date(timeStamp.time))

        return time.toString()
    }
}