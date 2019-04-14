package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentCreateEventBinding
import com.github.zeroicq.plannerator.mvp.presenters.CreateEventPresenter
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import com.github.zeroicq.plannerator.util.getGregorianCalendar

open class CreateEventFragment: MvpAppCompatFragment(), CreateEventView {
    enum class BUNDLE_KEYS { DATE }

    private lateinit var binding: FragmentCreateEventBinding

    @InjectPresenter
    lateinit var presenter: CreateEventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_event, container, false)

        val date = arguments!!.getGregorianCalendar(BUNDLE_KEYS.DATE.toString())

        return  binding.root
    }
}