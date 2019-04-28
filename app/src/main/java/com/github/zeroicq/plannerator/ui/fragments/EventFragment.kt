package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentEventBinding
import com.github.zeroicq.plannerator.mvp.models.EventModel
import com.github.zeroicq.plannerator.mvp.presenters.EventPresenter
import com.github.zeroicq.plannerator.mvp.views.EventView

class EventFragment : BaseFragment(), EventView {
    enum class BUNDLE_KEYS {EVENT}

    private lateinit var binding: FragmentEventBinding

    @InjectPresenter
    lateinit var presenter: EventPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)

        val event = arguments!!.getParcelable<EventModel>(BUNDLE_KEYS.EVENT.toString())!!

        binding.editButton.setOnClickListener { presenter.onEditButtonClick(event) }
        binding.deleteButton.setOnClickListener { presenter.onDeleteButtonClick(event) }

        val timeFormat = SimpleDateFormat("HH:mm")
        val startTime = timeFormat.format(event.startDate)
        val endTime = timeFormat.format(event.endDate)

        binding.dateText.text = "$startTime - $endTime"
        binding.eventTitle.text = event.title
        binding.eventMessage.text = event.message
        return binding.root
    }
}