package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.icu.text.SimpleDateFormat
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentCreateEventBinding
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.presenters.CreateEventPresenter
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import com.github.zeroicq.plannerator.util.getGregorianCalendar

open class CreateEventFragment: MvpAppCompatFragment(), CreateEventView, DatePickerFragment.DatePickerDialogListener {
    enum class BUNDLE_KEYS { DATE }

    private lateinit var binding: FragmentCreateEventBinding

    @InjectPresenter
    lateinit var presenter: CreateEventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_event, container, false)

        val date = arguments!!.getGregorianCalendar(BUNDLE_KEYS.DATE.toString())
        presenter.setInitialTimeInterval(date)

        binding.startDateTextView.setOnClickListener{ DatePickerFragment().show(fragmentManager, "datePicker") }
        binding.startTimeTextView.setOnClickListener{ TimePickerFragment().show(fragmentManager, "datePicker") }


        return binding.root
    }

    override fun setModelData(em: EventModel) {
        val dateFormat = SimpleDateFormat("EE, dd MMM yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")

        binding.startDateTextView.text = dateFormat.format(em.startDate)
        binding.startTimeTextView.text = timeFormat.format(em.startDate)

        binding.endDateTextView.text = dateFormat.format(em.endDate)
        binding.endTimeTextView.text = timeFormat.format(em.endDate)
    }

    override fun onDateSet(year: Int, month: Int, dayOfMonth: Int) {
        presenter.event.
    }


}