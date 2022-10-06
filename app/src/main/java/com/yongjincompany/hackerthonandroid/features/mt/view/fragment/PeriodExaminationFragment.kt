package com.yongjincompany.hackerthonandroid.features.mt.view.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseFragment
import com.yongjincompany.hackerthonandroid.databinding.FragmentPeriodExaminationBinding
import com.yongjincompany.hackerthonandroid.features.chat.view.ChatActivity
import com.yongjincompany.hackerthonandroid.features.mt.database.entity.MenstrualExaminationData
import com.yongjincompany.hackerthonandroid.features.mt.view.adapter.MenstrualExaminationAdapter
import com.yongjincompany.hackerthonandroid.features.mt.vm.PeriodExaminationViewModel

class MenstrualExaminationFragment :
    BaseFragment<FragmentPeriodExaminationBinding>(R.layout.fragment_period_examination) {

    private val periodExaminationViewModel by activityViewModels<PeriodExaminationViewModel>()
    private val questions = arrayListOf(MenstrualExaminationData("월경혈이 샐까봐 항상 불안하다"))
    private val menstrualExaminationAdapter by lazy { MenstrualExaminationAdapter { boolean -> addQuestion(boolean) } }
    private var type = ""

    override fun initView() {
        binding()
        recyclerViewSetting()
        observeEvent()
    }

    private fun binding() {
        binding.fragment = this
    }

    private fun observeEvent() {
        periodExaminationViewModel.type.observe(this) {
            animation()
        }
    }

    private fun animation() {
        binding.nextBtn.apply {
            visibility = View.VISIBLE
            alpha = 0f
            translationX = -50f
            animate().alpha(1f).translationXBy(50f).duration = 300
        }
    }

    private fun recyclerViewSetting() {
        menstrualExaminationAdapter.setData(questions)
        binding.questionRv.layoutManager = LinearLayoutManager(requireContext())
        binding.questionRv.adapter = menstrualExaminationAdapter
    }

    fun onClickNextBtn(view: View) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("type", periodExaminationViewModel.type.value)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun addQuestion(agree: String) {
        var agreeCode: String = agree
        if(questions.last().question == "월경혈이 샐까봐 항상 불안하다" && agreeCode == "true") {
            Log.d("TAG", "addQuestion")
            questions.add(MenstrualExaminationData("운동은 삶의 낙, 월경때도 쉴 수 없다."))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        } else if(questions.last().question == "월경혈이 샐까봐 항상 불안하다" && agreeCode == "false") {
            questions.add(MenstrualExaminationData("침대와 혼연일체 하고 싶은 마음 뿐"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        }
        if(questions.last().question == "운동은 삶의 낙, 월경때도 쉴 수 없다." && agreeCode == "true") {
            questions.add(MenstrualExaminationData("월경 용품을 살 때 성분 확인 필수"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        } else if(questions.last().question == "운동은 삶의 낙, 월경때도 쉴 수 없다." && agreeCode == "false"){
            questions.add(MenstrualExaminationData("침대와 혼연일체 하고 싶은 마음 뿐"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        }
        if(questions.last().question == "침대와 혼연일체 하고 싶은 마음 뿐" && agreeCode == "true") {
            questions.add(MenstrualExaminationData("월경 주기가 고통스럽다"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        } else if(questions.last().question == "침대와 혼연일체 하고 싶은 마음 뿐" && agreeCode == "false"){
            questions.add(MenstrualExaminationData("월경 용품을 살 때 성분 확인 필수"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        }
        if(questions.last().question == "월경 용품을 살 때 성분 확인 필수" && agreeCode == "true") {
            questions.add(MenstrualExaminationData("월경통은 언제 없어지는지 정말 알고 싶다"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        } else if(questions.last().question == "월경 용품을 살 때 성분 확인 필수" && agreeCode == "false"){
            periodExaminationViewModel.typeChange("ANSF")
        }
        if(questions.last().question == "월경 주기가 고통스럽다" && agreeCode == "true") {
            periodExaminationViewModel.typeChange("RNSP")
        } else if(questions.last().question == "월경 주기가 고통스럽다" && agreeCode == "false"){
            questions.add(MenstrualExaminationData("생리대를 쓸 때 따가움을 느낀다"))
            menstrualExaminationAdapter.setData(questions)
            agreeCode = ""
        }
        if(questions.last().question == "월경통은 언제 없어지는지 정말 알고 싶다" && agreeCode == "true") {
            periodExaminationViewModel.typeChange("AHSP")
        } else if(questions.last().question == "월경통은 언제 없어지는지 정말 알고 싶다" && agreeCode == "false") {
            periodExaminationViewModel.typeChange("AHSF")
        }
        if(questions.last().question == "생리대를 쓸 때 따가움을 느낀다" && agreeCode == "true") {
            periodExaminationViewModel.typeChange("RNSP")
        } else if(questions.last().question == "생리대를 쓸 때 따가움을 느낀다" && agreeCode == "false"){
            periodExaminationViewModel.typeChange("RNIF")
        }
    }


}