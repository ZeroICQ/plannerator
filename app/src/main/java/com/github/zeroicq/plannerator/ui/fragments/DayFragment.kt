package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentDayBinding
import com.github.zeroicq.plannerator.mvp.presenters.DayPresenter
import com.github.zeroicq.plannerator.mvp.views.DayView
import com.github.zeroicq.plannerator.util.getDayWithMonthWithYearString

class DayFragment: BaseFragment(), DayView {
    enum class BUNDLE_KEYS { DATE }

    private lateinit var binding: FragmentDayBinding

    @InjectPresenter
    lateinit var presenter: DayPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day, container, false)
        showFab = true
        activityView.setFabOnclickListener { view -> presenter.onCreateEvent() }

        presenter.date = (GregorianCalendar.getInstance() as GregorianCalendar)

        val dateInMillis = arguments?.getLong(BUNDLE_KEYS.DATE.toString())
        if (dateInMillis != null) {
            presenter.date.timeInMillis  = dateInMillis
        }
        setToolBarText(getDayWithMonthWithYearString(context!!, presenter.date))
        binding.dayView.eventClickListener = { it -> presenter.onEventClick(it)}
        binding.dayView.updateData(presenter.getDayModel(presenter.date))
        return  binding.root
    }
}