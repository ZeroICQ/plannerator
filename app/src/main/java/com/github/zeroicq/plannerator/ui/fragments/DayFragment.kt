package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.icu.util.GregorianCalendar
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
import com.github.zeroicq.plannerator.util.getDayWithMonthWithYeatString
import com.github.zeroicq.plannerator.util.getMonthWithYearString

class DayFragment: BaseFragment(), DayView {
    enum class BUNDLE_KEYS { DATE }

    private lateinit var binding: FragmentDayBinding

    //todo move to presenter?
    private lateinit var date: GregorianCalendar
//
    @InjectPresenter
    lateinit var presenter: DayPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false)

        date = (GregorianCalendar.getInstance() as GregorianCalendar)

        val dateInMillis = arguments?.getLong(BUNDLE_KEYS.DATE.toString())
        if (dateInMillis != null) {
            date.timeInMillis  = dateInMillis
        }
        setToolBarText(getDayWithMonthWithYeatString(context!!, date))
        binding.dayView.eventClickListener = { it -> presenter.onEventClick(it)}
        binding.dayView.updateData(presenter.getDayModel(date))
        return  binding.root
    }
}