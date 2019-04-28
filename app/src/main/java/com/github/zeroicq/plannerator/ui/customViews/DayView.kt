package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.icu.util.GregorianCalendar
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.EventModel

class DayView(ctxt : Context, attr: AttributeSet?) : ScrollView(ctxt, attr) {
    private val hourCells = ArrayList<HourCell>()
    var eventClickListener: ((EventModel)->Unit)? = null

    init {
        // hour table
        overScrollMode = View.OVER_SCROLL_NEVER
        isVerticalScrollBarEnabled = false

        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

//        scrollView.setBackgroundColor(Color.GRAY)
        isFillViewport = true


        val gridLayout = GridLayout(ctxt)
        gridLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)

        gridLayout.columnCount = 2
        gridLayout.rowCount = 24
//        gridView.setBackgroundColor(Color.RED)

        for (row in 1..24) {
            for (col in 1..2) {
                val hourCellLayout = LinearLayout(ctxt)
//                constraintLayout.setOnClickListener{onCellClick(it)}
                hourCellLayout.setBackgroundResource(R.drawable.day_cell_border)
                hourCellLayout.minimumHeight = 200
                hourCellLayout.orientation = LinearLayout.VERTICAL

                if (col == 1) {
                    hourCellLayout.gravity = Gravity.END or Gravity.BOTTOM
//                    hourCellLayout.setPadding(0, 0, 5, 0)
                    val hourTitleTextView = AppCompatTextView(ctxt).apply {
                        width = 150
                        if (row != 24)
                            text = "${row}:00"
                    }
                    hourTitleTextView.gravity = Gravity.END
                    hourCellLayout.addView(hourTitleTextView)

                    val lp = GridLayout.LayoutParams(
                        GridLayout.spec(row-1, GridLayout.FILL, 1.0f),
                        GridLayout.spec(col-1, GridLayout.FILL, 0f))

                    hourCellLayout.layoutParams = lp

                }
                else {
                    hourCellLayout.gravity = Gravity.CENTER_HORIZONTAL and Gravity.TOP
                    val cell = HourCell(hourCellLayout)
                    hourCells.add(cell)

                    val lp = GridLayout.LayoutParams(
                        GridLayout.spec(row-1, GridLayout.FILL, 1.0f),
                        GridLayout.spec(col-1, GridLayout.FILL, 1.0f))
                    lp.width = 0
                    hourCellLayout.layoutParams = lp
                }

//                val textView = AppCompatTextView(context)
//                textView.text = "tst"
////                cells.add(HourCell(textView))
//
//                hourCellLayout.addView(textView)

                gridLayout.addView(hourCellLayout)
            }
        }
        addView(gridLayout)
    }

    private fun onEventClick(eventModel: EventModel) {
        Log.d(PlanneratorApplication.appName, "clicked")
        eventClickListener?.invoke(eventModel)
    }

    fun updateData(currDay: DayModel) {
        val sortedEvents = currDay.events.sortedWith(compareBy({it.startDate.get(GregorianCalendar.HOUR_OF_DAY)},
            {it.startDate.get(GregorianCalendar.MINUTE)},
            {it.startDate.get(GregorianCalendar.SECOND)}))


        for (e in sortedEvents) {
            val currCell = hourCells[e.startDate.get(GregorianCalendar.HOUR_OF_DAY)]
            val eventView = EventPreviewView(context, e, true)
            eventView.minimumHeight = 100
            eventView.setOnClickListener{ onEventClick((e)) }
            currCell.layout.addView(eventView)
        }

    }

    class HourCell(val layout: ViewGroup) {
//        fun setData(dayModel: DayModel) {
//        }
    }
}