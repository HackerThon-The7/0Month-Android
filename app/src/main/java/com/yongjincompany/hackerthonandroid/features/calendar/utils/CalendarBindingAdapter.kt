package com.yongjincompany.hackerthonandroid.features.calendar.utils

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.yongjincompany.hackerthonandroid.common.getDayOfWeek
import com.yongjincompany.hackerthonandroid.common.getParentActivity
import java.time.LocalDate

@BindingAdapter("calendar_date")
fun setMutableDate(view: TextView, date: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    date.observe(parentActivity) { value ->
        val localDate = LocalDate.parse(value)
        view.text = "${localDate.monthValue}.${localDate.dayOfMonth} (${getDayOfWeek(localDate.dayOfWeek.value)})"
    }
}