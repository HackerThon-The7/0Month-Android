package com.yongjincompany.hackerthonandroid.features.mt.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseFragment
import com.yongjincompany.hackerthonandroid.databinding.FragmentPeriodExaminationBinding
import com.yongjincompany.hackerthonandroid.features.mt.database.entity.MenstrualExaminationData
import com.yongjincompany.hackerthonandroid.features.mt.view.adapter.MenstrualExaminationAdapter
import com.yongjincompany.hackerthonandroid.features.mt.vm.PeriodExaminationViewModel

class MenstrualExaminationFragment :
    BaseFragment<FragmentPeriodExaminationBinding>(R.layout.fragment_period_examination) {

    private val periodExaminationViewModel by activityViewModels<PeriodExaminationViewModel>()
    private val questions = arrayListOf(MenstrualExaminationData("월경혈이 샐까봐 항상 불안하다"))
    private val menstrualExaminationAdapter by lazy { MenstrualExaminationAdapter() }

    override fun initView() {
        binding()
        animation()
        recyclerViewSetting()
    }

    private fun binding() {
        binding.fragment = this
    }

    private fun animation() {
        binding.nextBtn.apply {
            alpha = 0f
            translationX = -50f
            animate().alpha(1f).translationXBy(50f).duration = 300
        }
    }

    private fun recyclerViewSetting() {
        binding.questionRv.adapter = menstrualExaminationAdapter
    }

    fun onClickNextBtn(view: View) {

    }

    private fun addQuestion() {
        if(true) {
            questions.add(MenstrualExaminationData("운동은 삶의 낙, 월경때도 쉴 수 없다."))
            menstrualExaminationAdapter.setData(questions)
        } else {
            questions.add(MenstrualExaminationData("침대와 혼연일체 하고 싶은 마음 뿐"))
            menstrualExaminationAdapter.setData(questions)
        }
        if(true) {
            questions.add(MenstrualExaminationData("월경 용품을 살 때 성분 확인 필수"))
            menstrualExaminationAdapter.setData(questions)
        } else {
            questions.add(MenstrualExaminationData("침대와 혼연일체 하고 싶은 마음 뿐"))
            menstrualExaminationAdapter.setData(questions)
        }
        if(true) {
            questions.add(MenstrualExaminationData("월경 용품을 살 때 성분 확인 필수"))
            menstrualExaminationAdapter.setData(questions)
        } else {
            questions.add(MenstrualExaminationData("월경 주기가 고통스럽다"))
            menstrualExaminationAdapter.setData(questions)
        }
        if(true) {
            questions.add(MenstrualExaminationData("월경통은 언제 없어지는지 정말 알고 싶다"))
            menstrualExaminationAdapter.setData(questions)
        } else {
            Log.d("TAG", "ANSF")
        }
        if(true) {
            Log.d("TAG", "RNSP")
        } else {
            questions.add(MenstrualExaminationData("생리대를 쓸 때 따가움을 느낀다"))
            menstrualExaminationAdapter.setData(questions)
        }
        if(true) {
            Log.d("TAG", "AHSP")
        } else {
            Log.d("TAG", "AHSF")
        }
        if(true) {
            Log.d("TAG", "RNSP")
        } else {
            Log.d("TAG", "RNIF")
        }
    }


}