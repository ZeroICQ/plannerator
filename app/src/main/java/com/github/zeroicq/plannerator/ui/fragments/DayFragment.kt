package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentDayBinding
import com.github.zeroicq.plannerator.mvp.presenters.DayPresenter
import com.github.zeroicq.plannerator.mvp.views.DayView

class DayFragment: MvpAppCompatFragment(), DayView {
    private lateinit var binding: FragmentDayBinding
//
    @InjectPresenter
    lateinit var presenter: DayPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false)
        return  binding.root
    }
}