package com.yongjincompany.hackerthonandroid.features.mt.view.fragment

import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.activityViewModels
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseFragment
import com.yongjincompany.hackerthonandroid.databinding.FragmentPeriodExaminationBinding
import com.yongjincompany.hackerthonandroid.features.mt.vm.PeriodExaminationViewModel

class MenstrualExaminationFragment :
    BaseFragment<FragmentPeriodExaminationBinding>(R.layout.fragment_period_examination) {

    private val periodExaminationViewModel by activityViewModels<PeriodExaminationViewModel>()
    private val questions = arrayListOf("월경혈이 샐까봐 항상 불안하다")

    override fun initView() {
        binding()
        animation()
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

    fun onClickNextBtn(view: View) {

    }

    private fun addQuestion() {
        if(true) {
            questions.add("운동은 삶의 낙, 월경때도 쉴 수 없다.")
        } else {
            questions.add("침대와 혼연일체 하고 싶은 마음 뿐")
        }
        if(true) {
            questions.add("월경 용품을 살 때 성분 확인 필수")
        } else {
            questions.add("침대와 혼연일체 하고 싶은 마음 뿐")
        }
        if(true) {
            questions.add("월경 용품을 살 때 성분 확인 필수")
        } else {
            questions.add("월경 주기가 고통스럽다")
        }
        if(true) {
            questions.add("월경통은 언제 없어지는지 정말 알고 싶다")
        } else {
            Log.d("TAG", "ANSF")
        }
        if(true) {
            Log.d("TAG", "RNSP")
        } else {
            questions.add("생리대를 쓸 때 따가움을 느낀다")
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