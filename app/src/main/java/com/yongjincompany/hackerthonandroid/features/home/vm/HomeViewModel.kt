package com.yongjincompany.hackerthonandroid.features.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class HomeViewModel : ViewModel() {

    val currentDate = MutableLiveData<Date>()
    val isWoman = MutableLiveData<Boolean>(true)

}