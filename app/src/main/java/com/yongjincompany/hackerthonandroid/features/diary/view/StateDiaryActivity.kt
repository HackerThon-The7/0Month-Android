package com.yongjincompany.hackerthonandroid.features.diary.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ActivityStateDiaryBinding
import com.yongjincompany.hackerthonandroid.features.diary.adapter.StateAdapter
import com.yongjincompany.hackerthonandroid.features.diary.item.State
import com.yongjincompany.hackerthonandroid.features.diary.vm.StateDiaryViewModel

class StateDiaryActivity : AppCompatActivity() {

    lateinit var binding: ActivityStateDiaryBinding
    lateinit var stateDiaryViewModel: StateDiaryViewModel
    lateinit var bodyStateAdapter: StateAdapter
    lateinit var behaviorStateAdapter: StateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initBodyStateAdapter()
        initBehaviorAdapter()
    }

    private fun initBodyStateAdapter() {
        bodyStateAdapter = StateAdapter()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.HORIZONTAL)
        binding.rvBodyState.layoutManager = staggeredGridLayoutManager
        binding.rvBodyState.adapter = bodyStateAdapter

        bodyStateAdapter.submitList(
            listOf(
                State("두통"), State("복통"), State("허리통증"), State("피부 트러블"),
                State("변비"), State("설사"), State("소화불량"), State("매스꺼움/구토"),
                State("복부 팽만"), State("민감한 가슴"), State("부종"), State("근육통"),
                State("발열"), State("오한"), State("손발저림"), State("어지러움")
            )
        )
    }

    private fun initBehaviorAdapter() {
        behaviorStateAdapter = StateAdapter()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
        binding.rvBehaviorChangeState.layoutManager = staggeredGridLayoutManager
        binding.rvBehaviorChangeState.adapter = behaviorStateAdapter

        behaviorStateAdapter.submitList(
            listOf(
                State("불안/초조"), State("예민/짜증"), State("우울/무기력"),
                State("기억력/집중력 저하"), State("수면장애")
            )
        )
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_state_diary)
        stateDiaryViewModel = ViewModelProvider(this)[StateDiaryViewModel::class.java]
        binding.vm = stateDiaryViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}