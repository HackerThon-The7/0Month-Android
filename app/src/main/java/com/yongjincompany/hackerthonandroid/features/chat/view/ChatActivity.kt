package com.yongjincompany.hackerthonandroid.features.chat.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseActivity
import com.yongjincompany.hackerthonandroid.databinding.ActivityChatBinding
import com.yongjincompany.hackerthonandroid.features.calendar.view.CalendarActivity
import com.yongjincompany.hackerthonandroid.features.chat.data.Message
import com.yongjincompany.hackerthonandroid.features.chat.logic.BotResponse
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_GOOGLE
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_SEARCH
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.RECEIVE_ID
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.SEND_ID
import com.yongjincompany.hackerthonandroid.features.chat.logic.Time
import com.yongjincompany.hackerthonandroid.features.mt.view.PeriodExaminationActivity
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class ChatActivity : BaseActivity<ActivityChatBinding>(
    R.layout.activity_chat
) {
    private lateinit var adapter: MessagingAdapter
    var result: String = ""

    private var a = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        result = intent.getStringExtra("type").toString()

        when (result) {
            "AHSP" -> {
                customMessage("제가 분석해본 결과..다른 사람보다 월경을 조금 더 유난스럽게 겪는 사람이군요")
            }
            "AHSF" -> {
                customMessage("활동적인 라이프스타일과 민감한 피부 타입에 꼭 맞는 체내형 월경 용품 추천이 필요한 사람이군요")
            }
            "ANSF" -> {
                customMessage("월경 기간에는 평소와 달리 활동적인 라이프스타일을 유지하기 힘든 사람이군요")
            }
            "RNIF" -> {
                customMessage("정적인 라이프스타일을 유지하고, 조금은 고통에서 자유로운 사람이군요")
            }
            "RNSP" -> {
                customMessage("민감한 피부에 월경통까지, 정적인 라이프스타일을 유지할 수 밖에 없는 사람이군요")
            }
            else -> {
                customMessage("안녕? 오늘 너와 얘기할 영월이야, 나한테 할 말이 있니?")
            }
        }
        recyclerView()
        clickEvents()
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()

                adapter.insertMessage(Message(message = String(), RECEIVE_ID, timeStamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }


    override fun initView() {
        val fadeInAnim = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in)

        binding.addView.visibility = View.GONE

        binding.btnAdd.setOnClickListener {
            binding.addView.visibility = View.VISIBLE
            binding.btnCalender.visibility = View.VISIBLE
            binding.btnDoctor.startAnimation(fadeInAnim)
            binding.btnDoctor.visibility = View.VISIBLE

            if (a == 1) {
                binding.addView.visibility = View.GONE
                binding.btnCalender.visibility = View.GONE
                binding.btnDoctor.visibility = View.GONE
                a = 0
            } else {
                a = 1
            }
        }

        binding.btnCalender.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            overridePendingTransition(com.google.android.material.R.anim.abc_slide_in_bottom,
                com.google.android.material.R.anim.abc_slide_out_top)
            startActivity(intent)
        }

        binding.btnDoctor.setOnClickListener {
            val intent = Intent(this, PeriodExaminationActivity::class.java)
            overridePendingTransition(com.google.android.material.R.anim.abc_slide_in_bottom,
                com.google.android.material.R.anim.abc_slide_out_top)
            startActivity(intent)
        }

    }

    private fun customMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timestamp = Time.timeStamp()
                adapter.insertMessage(Message(message, RECEIVE_ID, timestamp))

                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun clickEvents() {
        binding.btnSend.setOnClickListener {
            sendMessage()
        }

        binding.etMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage() {
        val message = binding.etMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            binding.etMessage.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))



                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)

                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }
}