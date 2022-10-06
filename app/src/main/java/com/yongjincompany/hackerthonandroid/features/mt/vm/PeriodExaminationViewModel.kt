package com.yongjincompany.hackerthonandroid.features.mt.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeriodExaminationViewModel: ViewModel() {

    val type: LiveData<String> get() = _type
    private val _type = MutableLiveData<String>()

    fun typeChange(type: String) {
        _type.value = type
    }

}