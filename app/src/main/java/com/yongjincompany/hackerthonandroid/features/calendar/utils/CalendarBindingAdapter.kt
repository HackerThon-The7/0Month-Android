package com.yongjincompany.hackerthonandroid.features.calendar.utils

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.yongjincompany.hackerthonandroid.common.getDayOfWeek
import com.yongjincompany.hackerthonandroid.common.getParentActivity
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("calendar_date")
fun setMutableDate(view: TextView, date: MutableLiveData<String>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    date.observe(parentActivity) { value ->
        val localDate = LocalDate.parse(value)
        view.text = "${localDate.monthValue}.${localDate.dayOfMonth} (${getDayOfWeek(localDate.dayOfWeek.value)})"
    }
}