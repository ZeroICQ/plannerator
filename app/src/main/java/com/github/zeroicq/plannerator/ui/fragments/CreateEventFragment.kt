package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.content.Intent
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

open class CreateEventFragment: MvpAppCompatFragment(), CreateEventView {
    val START_DATE_REQUEST = 1
    val END_DATE_REQUEST = 2

    val START_TIME_REQUEST = 3
    val END_TIME_REQUEST = 4

    enum class BUNDLE_KEYS { DATE }

    private lateinit var binding: FragmentCreateEventBinding

    @InjectPresenter
    lateinit var presenter: CreateEventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_event, container, false)

        val date = arguments!!.getGregorianCalendar(BUNDLE_KEYS.DATE.toString())
        presenter.setInitialTimeInterval(date)

        binding.startDateTextView.setOnClickListener{ openDatePickerDialog(START_DATE_REQUEST) }
        binding.endDateTextView.setOnClickListener{ openDatePickerDialog(END_DATE_REQUEST) }

        binding.startTimeTextView.setOnClickListener{ TimePickerFragment().show(fragmentManager, "datePicker") }
        return binding.root
    }

    private fun openDatePickerDialog(requestCode: Int) {
        val fragment = DatePickerFragment()
        fragment.setTargetFragment(this, requestCode)
        fragment.show(fragmentManager, "DatePicker")
//        DatePickerFragment().show(fragmentManager, "datePicker")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            //todo
            val year = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.YEAR.toString(), -1)
            val month = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.MONTH.toString(), -1)
            val dayOfMonth = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.DAY_OF_MONTH.toString(), -1)

            when (requestCode) {
                START_DATE_REQUEST -> {
                    presenter.event.startDate.set(GregorianCalendar.YEAR, year!!)
                    presenter.event.startDate.set(GregorianCalendar.MONTH, month!!)
                    presenter.event.startDate.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth!!)
                }

                END_DATE_REQUEST -> {
                    presenter.event.endDate.set(GregorianCalendar.YEAR, year!!)
                    presenter.event.endDate.set(GregorianCalendar.MONTH, month!!)
                    presenter.event.endDate.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth!!)
                }

            }

            presenter.updateEvent()
        }
    }

    override fun setModelData(em: EventModel) {
        val dateFormat = SimpleDateFormat("EE, dd MMM yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")

        binding.startDateTextView.text = dateFormat.format(em.startDate)
        binding.startTimeTextView.text = timeFormat.format(em.startDate)

        binding.endDateTextView.text = dateFormat.format(em.endDate)
        binding.endTimeTextView.text = timeFormat.format(em.endDate)
    }

}