package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentWeekBinding
import com.github.zeroicq.plannerator.mvp.presenters.WeekPresenter
import com.github.zeroicq.plannerator.mvp.views.WeekView

class WeekFragment : MvpAppCompatFragment(), WeekView {
    private lateinit var binding: FragmentWeekBinding
    //
    @InjectPresenter
    lateinit var presenter: WeekPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week, container, false)

        return binding.root
    }
}