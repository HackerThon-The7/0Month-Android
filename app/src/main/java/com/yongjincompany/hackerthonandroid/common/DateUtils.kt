package com.yongjincompany.hackerthonandroid.common

fun getDayOfWeek(dayOfWeek: Int) : String = when(dayOfWeek) {
    1 -> "월"
    2 -> "화"
    3 -> "수"
    4 -> "목"
    5 -> "금"
    6 -> "토"
    7 -> "일"
    else -> "?"
}
