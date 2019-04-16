package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.icu.text.SimpleDateFormat
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentCreateEventBinding
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.presenters.CreateEventPresenter
import com.github.zeroicq.plannerator.mvp.views.CreateEventView
import com.github.zeroicq.plannerator.util.getGregorianCalendar
import com.github.zeroicq.plannerator.util.putGregorianCalendar

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

        val date = arguments?.getGregorianCalendar(BUNDLE_KEYS.DATE.toString()) ?: (GregorianCalendar.getInstance() as GregorianCalendar)
        presenter.setInitialTimeInterval(date)

        // edittexts listeners
        binding.eventTitle.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/* do nothing */}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/* do nothing */}

            override fun afterTextChanged(s: Editable?) {
                presenter.event.title = s.toString()
                presenter.updateEvent()
            }
        })

        binding.eventMessage.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/* do nothing */}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/* do nothing */}

            override fun afterTextChanged(s: Editable?) {
                presenter.event.message = s.toString()
                presenter.updateEvent()
            }
        })

        // pickers listeners
        binding.startDateTextView.setOnClickListener{ openDatePickerDialog(START_DATE_REQUEST, presenter.event.startDate) }
        binding.endDateTextView.setOnClickListener{ openDatePickerDialog(END_DATE_REQUEST, presenter.event.endDate) }

        binding.startTimeTextView.setOnClickListener{ openTimePickerDialog(START_TIME_REQUEST, presenter.event.startDate) }
        binding.endTimeTextView.setOnClickListener{ openTimePickerDialog(END_TIME_REQUEST, presenter.event.endDate) }

        // saveEvent button
        binding.saveButton.setOnClickListener { view ->  hideKeyboard(activity!!); presenter.saveEvent()}
        return binding.root
    }

    private fun openDatePickerDialog(requestCode: Int, date: GregorianCalendar) {
        val fragment = DatePickerFragment()

        val bundle = Bundle()
        bundle.putGregorianCalendar(DatePickerFragment.BUNDLE_KEYS.DATE.toString(), date)
        fragment.arguments = bundle

        fragment.setTargetFragment(this, requestCode)
        fragment.show(fragmentManager, "DatePicker")
    }

    private fun openTimePickerDialog(requestCode: Int, date: GregorianCalendar) {
        val fragment = TimePickerFragment()

        val bundle = Bundle()
        bundle.putGregorianCalendar(TimePickerFragment.BUNDLE_KEYS.DATE.toString(), date)
        fragment.arguments = bundle

        fragment.setTargetFragment(this, requestCode)
        fragment.show(fragmentManager, "TimePicker")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            //todo
            val year = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.YEAR.toString(), -1)
            val month = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.MONTH.toString(), -1)
            val dayOfMonth = data?.getIntExtra(DatePickerFragment.INTENT_KEYS.DAY_OF_MONTH.toString(), -1)

            val hourOfDay = data?.getIntExtra(TimePickerFragment.INTENT_KEYS.HOUR_OF_DAY.toString(), -1)
            val minute = data?.getIntExtra(TimePickerFragment.INTENT_KEYS.MINUTE.toString(), -1)

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

                START_TIME_REQUEST -> {
                    presenter.event.startDate.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay!!)
                    presenter.event.startDate.set(GregorianCalendar.MINUTE, minute!!)
                }

                END_TIME_REQUEST -> {
                    presenter.event.endDate.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay!!)
                    presenter.event.endDate.set(GregorianCalendar.MINUTE, minute!!)
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

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}