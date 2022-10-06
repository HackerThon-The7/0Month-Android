package com.yongjincompany.hackerthonandroid.features.chat.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseActivity
import com.yongjincompany.hackerthonandroid.databinding.ActivityChatBinding
import com.yongjincompany.hackerthonandroid.features.chat.data.Message
import com.yongjincompany.hackerthonandroid.features.chat.logic.BotResponse
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_GOOGLE
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.OPEN_SEARCH
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.RECEIVE_ID
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.SEND_ID
import com.yongjincompany.hackerthonandroid.features.chat.logic.Time
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class ChatActivity : BaseActivity<ActivityChatBinding>(
    R.layout.activity_chat
) {
    private lateinit var adapter: MessagingAdapter

    private var a = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView()
        clickEvents()

        customMessage("안녕? 오늘 너와 얘기할 영월이야, 나한테 할 말이 있니?")
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
                binding.rvMessages.scrollToPosition(adapter.itemCount - 1)

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