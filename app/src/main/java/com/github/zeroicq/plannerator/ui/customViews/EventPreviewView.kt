package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.zeroicq.plannerator.mvp.models.EventModel

class EventPreviewView(ctxt: Context, event: EventModel) : CardView(ctxt) {
//    var dayClickListener: ((DayModel)->Unit)? = null
//    private val cells = ArrayList<Cell>()

    init {
        val lp = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(5, 0, 5, 10)

        layoutParams = lp

        setBackgroundColor(Color.GREEN)

        val textView = AppCompatTextView(ctxt)
        textView.text = event.message
        addView(textView)
    }


}