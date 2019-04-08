package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.mvp.models.EventModel

class EventPreviewView(ctxt: Context, event: EventModel) : CardView(ctxt) {
//    var dayClickListener: ((DayModel)->Unit)? = null
//    private val cells = ArrayList<Cell>()

    init {
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(10, 5, 10, 20)

        layoutParams = lp

        setBackgroundColor(ctxt.getColor(R.color.highlightDay))

        val textView = AppCompatTextView(ctxt)
        textView.text = event.title
        addView(textView)
    }


}