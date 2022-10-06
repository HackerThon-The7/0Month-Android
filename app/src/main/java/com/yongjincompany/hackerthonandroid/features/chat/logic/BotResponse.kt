package com.yongjincompany.hackerthonandroid.features.chat.logic

import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_GOOGLE
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_SEARCH

object BotResponse {
    fun basicResponses(_message: String): String {
        val random = (0..5).random()
        val message = _message.toLowerCase()

        return when {
            message.contains("안녕") -> {
                when (random) {
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
                when (random) {
                    0 -> "너무 힘들어ㅠㅠ 오늘만 100번째 대화 중이야"
                    1 -> "기모찌~"
                    2 -> "오늘 기분 좋았지 넌 어때?"
                    3 -> "행복해"
                    4 -> "배고파 많이~"
                    5 -> "심심해.."
                    else -> "error"
                }
            }

            message.contains("생리통") -> {
                when (random) {
                    0 -> "일단 맛있는 음식을 피해! 치킨 튀김 같은 거 있잖아. 그거 말고 상큼한 복숭아가 더 좋을꺼야."
                    1 -> "카페인에 의존하면 안돼! 술처먹지 말고 빨리 자"
                    2 -> "뭘 어떡해 그냥 버텨 시간이 약이여.."
                    3 -> "진짜진짜 너~~무 힘들면 내가 진통제 사줄께 생리통이 심하면 진통제 먹는게 나을 수 있어. 난 너가 아픈거 싫은데ㅠㅠ"
                    4 -> "석류, 바나나, 미역, 부추가 통증에 좋대 곱게 갈은 바나나 쉐이크를 먹어서 달달함과 통증을 완화시켜봐,"
                    5 -> "그... 나랑 스트레칭 할래? 근육도 풀리고 좋은데"
                    else -> "error"
                }
            }

            message.contains("음식") -> {
                when (random) {
                    0 -> "브로콜리 묵으래이~"
                    1 -> "좀 쓰긴한데 다크 초콜릿 어뗘? 은근 맛있던데.."
                    2 -> "연어 초밥 먹자 연어가 입에서 막 날뛰는 그 기분이 그립다"
                    3 -> "마시는 걸 원한다면 차를 마셔보는거 어때? 카모마일 차, 녹차, 페퍼민트차, 똥차, 폐차 등등(?)"
                    4 -> "귤 어때? 까먹다보면 아픈지도 모를껄...???"
                    5 -> "석류가 그렇게 좋다는데 아 누가 석류 안 구해주나~~"
                    else -> "error"
                }
            }

            message.startsWith("설명!") -> {
                "난 영월! 앞에 이 말을 붙여봐!!\n1.행운동전 (운을 실험해요!)\n2.계산 (사칙연산 쌉가능!)\n3.시간 (현재 시간을 알려줘요)\n4.검색 (원하는걸 입력)\n5.어떡하지.. (걱정을 덜어줄꺼에요)"
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
                    0 -> "이해가 안돼..."
                    1 -> "어쩔티비"
                    2 -> "뭐라는거야"
                    3 -> "듣기 싫어"
                    4 -> "뭐라고 했어?"
                    5 -> "내 지식수준은 2살이라 너가 방금 말한게 이해가 안됨.."
                    else -> "error"
                }
            }
        }
    }
}