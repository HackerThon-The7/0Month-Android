package com.yongjincompany.hackerthonandroid.common

fun getDayOfWeek(dayOfWeek: Int) : String = when(dayOfWeek) {
    1 -> "월요일"
    2 -> "화요일"
    3 -> "수요일"
    4 -> "목요일"
    5 -> "금요일"
    6 -> "토요일"
    7 -> "일요일"
    else -> "?"
}
