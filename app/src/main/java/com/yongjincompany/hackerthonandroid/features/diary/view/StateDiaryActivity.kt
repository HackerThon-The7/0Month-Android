package com.yongjincompany.hackerthonandroid.features.diary.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.database.RoomDatabase
import com.yongjincompany.hackerthonandroid.databinding.ActivityStateDiaryBinding
import com.yongjincompany.hackerthonandroid.features.diary.adapter.StateAdapter
import com.yongjincompany.hackerthonandroid.features.diary.item.State
import com.yongjincompany.hackerthonandroid.features.diary.vm.StateDiaryViewModel
import java.time.LocalDate

class StateDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityStateDiaryBinding
    private lateinit var stateDiaryViewModel: StateDiaryViewModel
    private lateinit var bodyStateAdapter: StateAdapter
    private lateinit var behaviorStateAdapter: StateAdapter

    private lateinit var date: String

    private var isCheckedHappy: Boolean = true

    private val bodyStates: List<State> = listOf(
        State("두통"), State("복통"), State("허리통증"), State("피부 트러블"),
        State("변비"), State("설사"), State("소화불량"), State("매스꺼움/구토"),
        State("복부 팽만"), State("민감한 가슴"), State("부종"), State("근육통"),
        State("발열"), State("오한"), State("손발저림"), State("어지러움")
    )

    private val behaviorStates: List<State> = listOf(
        State("불안/초조"), State("예민/짜증"), State("우울/무기력"),
        State("기억력/집중력 저하"), State("수면장애")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        stateDiaryViewModel.database = RoomDatabase.getInstance(this)
        date = intent.getStringExtra("date") ?: LocalDate.now().toString()
        initBodyStateAdapter()
        initBehaviorAdapter()
        bindingView()
    }

    private fun bindingView() {
        binding.layoutHappy.backgroundTintList = ContextCompat.getColorStateList(this, R.color.checked_yellow)
        binding.layoutHappy.setOnClickListener {
            if (isCheckedHappy.not()) {
                isCheckedHappy = true
                binding.layoutHappy.backgroundTintList = ContextCompat.getColorStateList(this, R.color.checked_yellow)
                binding.layoutSad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
            }
        }

        binding.layoutSad.setOnClickListener {
            if (isCheckedHappy) {
                isCheckedHappy = false
                binding.layoutSad.backgroundTintList = ContextCompat.getColorStateList(this, R.color.checked_yellow)
                binding.layoutHappy.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.layoutSave.setOnClickListener {

            var hasBodyStateChecked: Boolean = false
            bodyStateAdapter.currentList.forEach {
                if (it.isChecked) {
                    hasBodyStateChecked = true
                }
            }

            var hasBehaviorStateChecked: Boolean = false
            behaviorStateAdapter.currentList.forEach {
                if (it.isChecked) {
                    hasBehaviorStateChecked = true
                }
            }

            if (hasBehaviorStateChecked && hasBodyStateChecked) {
                stateDiaryViewModel.saveState(
                    date = date,
                    bodyState = bodyStateAdapter.currentList.filter { it.isChecked }[0].type,
                    mood = if (isCheckedHappy) "좋아요!" else "나빠요..",
                    behaviorChange = behaviorStateAdapter.currentList.filter { it.isChecked }[0].type
                )
            } else {
                Toast.makeText(this, "모두 체크해 주세요!", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    private fun initBodyStateAdapter() {
        bodyStateAdapter = StateAdapter { type ->
            val list = bodyStateAdapter.currentList.map {
                it.isChecked = it.type == type
                it
            }

            bodyStateAdapter.submitList(list.sortedBy { it.isChecked.not() })
        }

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.HORIZONTAL)
        binding.rvBodyState.layoutManager = staggeredGridLayoutManager
        binding.rvBodyState.adapter = bodyStateAdapter

        bodyStateAdapter.submitList(bodyStates)
    }

    private fun initBehaviorAdapter() {
        behaviorStateAdapter = StateAdapter { type ->
            val list = behaviorStateAdapter.currentList.map {
                it.isChecked = it.type == type
                it
            }

            behaviorStateAdapter.submitList(list.sortedBy { it.isChecked.not() })
        }
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
        binding.rvBehaviorChangeState.layoutManager = staggeredGridLayoutManager
        binding.rvBehaviorChangeState.adapter = behaviorStateAdapter

        behaviorStateAdapter.submitList(behaviorStates)
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_diary)
        stateDiaryViewModel = ViewModelProvider(this)[StateDiaryViewModel::class.java]
        binding.vm = stateDiaryViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}