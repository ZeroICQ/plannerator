package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentEventBinding
import com.github.zeroicq.plannerator.mvp.presenters.EventPresenter
import com.github.zeroicq.plannerator.mvp.views.EventView

class EventFragment : BaseFragment(), EventView {
    private lateinit var binding: FragmentEventBinding
    //
    @InjectPresenter
    lateinit var presenter: EventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        return  binding.root
    }
}