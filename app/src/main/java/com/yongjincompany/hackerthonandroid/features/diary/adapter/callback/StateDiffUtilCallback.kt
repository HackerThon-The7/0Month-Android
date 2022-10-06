package com.yongjincompany.hackerthonandroid.features.diary.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.yongjincompany.hackerthonandroid.features.diary.item.State

object StateDiffUtilCallback : DiffUtil.ItemCallback<State>() {
    override fun areItemsTheSame(oldItem: State, newItem: State): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: State, newItem: State): Boolean {
        return oldItem.isChecked != newItem.isChecked
    }
}