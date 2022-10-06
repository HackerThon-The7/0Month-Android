package com.yongjincompany.hackerthonandroid.features.calendar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ActivityCalendarBinding
import com.yongjincompany.hackerthonandroid.features.calendar.vm.CalendarViewModel

class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding
    lateinit var calendarViewModel: CalendarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        observeViewModel()
        bindingView()
    }

    private fun observeViewModel() = with(calendarViewModel) {

    }

    private fun bindingView() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendarViewModel.currentDate.value = "${year}-${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}"
        }
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar)
        calendarViewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        binding.vm = calendarViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}