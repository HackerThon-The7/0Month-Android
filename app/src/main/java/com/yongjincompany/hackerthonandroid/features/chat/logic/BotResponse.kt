package com.yongjincompany.hackerthonandroid.features.chat.logic

import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_GOOGLE
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_SEARCH

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..5).random()
        val message = _message.toLowerCase()

        return when {
            message.contains("안녕") -> {
                when(random) {
                    0 -> "안녕?"
                    1 -> "안녕하세유"
                    2 -> "헬로우 월드!!"
                    3 -> "안녕 방가워"
                    4 -> "안녕 못하겠는데.."
                    5 -> "👋"
                    else -> "error"
                }
            }

            message.contains("오늘 어때?") -> {
                when(random) {
                    0 -> "너무 힘들다 오늘만 100명째..."
                    1 -> "기모찌~"
                    2 -> "오늘 기분 좋았지 넌 어때?"
                    3 -> "행복해"
                    4 -> "배고파 많이~"
                    5 -> "심심해.."
                    else -> "error"
                }
            }

            message.contains("연애") -> {
                when(random) {
                    0 -> "오늘 내 마음을 고백하는 날??"
                    1 -> "오늘은 좋아하는 사람에게 말과 행동을 조심하세요"
                    2 -> "연애 넌 평생 못할듯합니다"
                    3 -> "당신이 한없이 좋아한다면 상대방도 당신을 좋아하고 있을꺼에요"
                    4 -> "혼자 마음고생 심하게 하는 것보다 일을 치르는게 낫다"
                    5 -> "연애는 그리워하고 애정하다의 뜻으로, \n서로를 사랑하는 두 사람 사이의 친밀한 관계를 말하며,\n 마음에 들어서 만나는 것도 연애라고 한다"
                    else -> "error"
                }
            }

            message.startsWith("설명!") -> {
                "용진봇입니다! 앞에 이 말을 붙여봐요!!\n1.행운동전 (운을 실험해요!)\n2.계산 (사칙연산 쌉가능!)\n3.시간 (현재 시간을 알려줘요)\n4.검색 (원하는걸 입력)\n5.연애 (연애운을 알려줘요)"
            }

            message.contains("행운동전") -> {
                var r = (0..1).random()
                val result = if (r == 0) "앞면" else "뒷면"

                "${result}이 나왔네요!"
            }

            message.contains("시간") -> {
                Time.timeStamp()
            }

            message.startsWith("들어가줘") && message.contains("구글") -> {
                OPEN_GOOGLE
            }

            message.startsWith("검색") -> {
                OPEN_SEARCH
            }

            else -> {
                when (random) {
                    0 -> "이해가 안되..."
                    1 -> "어쩔티비"
                    2 -> "뭐라는거야"
                    3 -> "듣기 싫어"
                    4 -> "난 그런거 모르고 살아"
                    5 -> "내 지식수준은 2살이라 이해가 안됨.."
                    else -> "error"
                }
            }
        }
    }
}