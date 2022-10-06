package com.yongjincompany.hackerthonandroid.features.diary.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ItemStateBinding
import com.yongjincompany.hackerthonandroid.features.diary.adapter.callback.StateDiffUtilCallback
import com.yongjincompany.hackerthonandroid.features.diary.item.State

class StateAdapter(val onClickItem: (type: String) -> Unit) : ListAdapter<State, StateAdapter.StateViewHolder>(StateDiffUtilCallback) {

    inner class StateViewHolder(private val binding: ItemStateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(state: State) {
            binding.state = state

            binding.root.setOnClickListener {
                onClickItem.invoke(state.type)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        return StateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_state,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}