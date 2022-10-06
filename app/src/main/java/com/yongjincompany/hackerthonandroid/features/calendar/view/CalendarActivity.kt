package com.yongjincompany.hackerthonandroid.features.calendar.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.ActivityCalendarBinding
import com.yongjincompany.hackerthonandroid.features.calendar.vm.CalendarViewModel

class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding
    lateinit var calendarViewModel: CalendarViewModel

    private var isFabOpen = false

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

        binding.fabAdd.setOnClickListener {
            toggleFab()
        }

    }

    private fun toggleFab() {
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabDiary, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPeriodEnd, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPeriodStart, "translationY", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.tvDiary, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.tvPeriodEnd, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.tvPeriodStart, "translationY", 0f).apply { start() }

            binding.tvDiary.visibility = View.GONE
            binding.tvPeriodEnd.visibility = View.GONE
            binding.tvPeriodStart.visibility = View.GONE

            binding.fabAdd.setImageResource(R.drawable.ic_add)
        } else {
            ObjectAnimator.ofFloat(binding.fabDiary, "translationY", -150f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPeriodEnd, "translationY", -300f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPeriodStart, "translationY", -450f).apply { start() }

            ObjectAnimator.ofFloat(binding.tvDiary, "translationY", -150f).apply { start() }
            ObjectAnimator.ofFloat(binding.tvPeriodEnd, "translationY", -300f).apply { start() }
            ObjectAnimator.ofFloat(binding.tvPeriodStart, "translationY", -450f).apply { start() }

            binding.tvDiary.visibility = View.VISIBLE
            binding.tvPeriodEnd.visibility = View.VISIBLE
            binding.tvPeriodStart.visibility = View.VISIBLE

            binding.fabAdd.setImageResource(R.drawable.ic_clear)
        }

        isFabOpen = !isFabOpen

    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar)
        calendarViewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        binding.vm = calendarViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}