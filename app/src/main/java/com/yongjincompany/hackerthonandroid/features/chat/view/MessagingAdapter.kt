package com.yongjincompany.hackerthonandroid.features.chat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.MessageItemBinding
import com.yongjincompany.hackerthonandroid.features.chat.data.Message
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.RECEIVE_ID
import com.yongjincompany.hackerthonandroid.features.chat.logic.Constants.SEND_ID

class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messageList = mutableListOf<Message>()

    inner class MessageViewHolder(messageItemBinding: MessageItemBinding) : RecyclerView.ViewHolder(messageItemBinding.root) {
        init {
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MessageViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.message_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tvBotMessage.visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.itemView.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tvMessage.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun insertMessage(message: Message) {
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }
}