package com.yongjincompany.hackerthonandroid.features.mt.view.fragment

import android.view.View
import androidx.fragment.app.activityViewModels
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.base.BaseFragment
import com.yongjincompany.hackerthonandroid.databinding.FragmentPeriodExaminationBinding
import com.yongjincompany.hackerthonandroid.features.mt.vm.PeriodExaminationViewModel

class MenstrualExaminationFragment :
    BaseFragment<FragmentPeriodExaminationBinding>(R.layout.fragment_period_examination) {

    private val periodExaminationViewModel by activityViewModels<PeriodExaminationViewModel>()

    override fun initView() {
        binding()
    }
    private fun binding() {
        binding.fragment = this
    }

    fun onClickNextBtn(view: View) {

    }
}