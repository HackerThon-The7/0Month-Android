package com.yongjincompany.hackerthonandroid.features.mt.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ItemQuestionBinding
import com.yongjincompany.hackerthonandroid.features.mt.database.entity.MenstrualExaminationData

class MenstrualExaminationAdapter(val onClick: (isAgree: String) -> Unit) :
    RecyclerView.Adapter<MenstrualExaminationAdapter.MenstrualExaminationViewHolder>() {

    private var oldList = emptyList<MenstrualExaminationData>()

    inner class MenstrualExaminationViewHolder(itemView: ItemQuestionBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val questionTextView: TextView = itemView.questionTv
        private var question: MenstrualExaminationData? = null
        private val radioGroup = itemView.radioGroup

        fun bind(menstrualExaminationData: MenstrualExaminationData) {
            question = menstrualExaminationData
            questionTextView.text = question!!.question
            radioGroup.setOnCheckedChangeListener { _, clickedId ->
                onClick.invoke( when (clickedId) { R.id.agreeBtn -> "true" else -> "false" } )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenstrualExaminationViewHolder {
        return MenstrualExaminationViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_question,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenstrualExaminationViewHolder, position: Int) {
        holder.bind(oldList[position])
    }

    override fun getItemCount(): Int = oldList.size

    fun setData(newList: List<MenstrualExaminationData>) {
        Log.d("TAG", "setData")
        val diffUtil = MenstrualExaminationDiffUtil(oldList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResults.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}

class MenstrualExaminationDiffUtil(
    private val oldList: List<MenstrualExaminationData>,
    private val newList: List<MenstrualExaminationData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].question == newList[newItemPosition].question

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].question == newList[newItemPosition].question
}