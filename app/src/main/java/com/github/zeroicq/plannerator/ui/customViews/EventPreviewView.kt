package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.EventDayPreivewBinding
import com.github.zeroicq.plannerator.mvp.models.EventModel

class EventPreviewView(ctxt: Context, event: EventModel, showTime: Boolean = false) : CardView(ctxt) {
//    var dayClickListener: ((DayModel)->Unit)? = null
//    private val cells = ArrayList<Cell>()

    init {
        val lp = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams = lp

        val inflater = LayoutInflater.from(ctxt)
        val binding: EventDayPreivewBinding = DataBindingUtil.inflate(inflater, R.layout.event_day_preivew, this, false)
        lp.setMargins(10, 5, 10, 20)

        setBackgroundColor(ctxt.getColor(R.color.highlightDay))

//        val dateFormat = SimpleDateFormat("EE, dd MMM yyyy")
        val timeFormat = SimpleDateFormat("HH:mm")
        val startTime =  timeFormat.format(event.startDate)
        val endTime =  timeFormat.format(event.endDate)
        binding.timeSegment.text = "$startTime - $endTime"
        binding.eventTitle.text = event.title
        
        if(!showTime)
            binding.timeSegment.visibility = View.GONE

        addView(binding.root)
    }




}