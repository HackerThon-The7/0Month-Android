package com.yongjincompany.hackerthonandroid.features.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.hackerthonandroid.R
import com.yongjincompany.hackerthonandroid.databinding.FragmentHomeBinding
import com.yongjincompany.hackerthonandroid.features.home.vm.HomeViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        performViewModel()
        observeViewModel()
        bindingView()

        return binding.root

    }

    private fun observeViewModel() = with(homeViewModel) {

    }

    private fun bindingView() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            homeViewModel.currentDate.value = "${year}-${String.format("%02d", month + 1)}-${String.format("%02d", dayOfMonth)}"
        }
    }

    private fun performViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.vm = homeViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}