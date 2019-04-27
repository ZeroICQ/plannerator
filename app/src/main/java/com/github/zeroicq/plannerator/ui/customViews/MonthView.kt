package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.icu.util.GregorianCalendar
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.util.isSameDay
import com.github.zeroicq.plannerator.util.weekdayRes
import java.util.*

class MonthView(ctxt: Context) : GridLayout(ctxt) {
    var dayClickListener: ((DayModel)->Unit)? = null

    private val cells = ArrayList<Cell>()

    init {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        rowCount = 7
        columnCount = 7

        for ((i, month) in (GregorianCalendar.MONDAY..GregorianCalendar.SATURDAY + 1).withIndex()) {
            val constraintLayout = LinearLayout(ctxt)
            val textView = AppCompatTextView(context)
            val lp = GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 0.1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1.0f)).apply {
                setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM)
//                rowCount = 1
//                columnCount = i+1
            }

            constraintLayout.layoutParams = lp

            constraintLayout.setBackgroundResource(R.drawable.day_cell_border)
            constraintLayout.gravity = Gravity.CENTER_HORIZONTAL
            constraintLayout.setBackgroundResource(R.drawable.day_cell_border)

            constraintLayout.addView(textView)
            addView(constraintLayout)

            textView.setText( if (month != GregorianCalendar.SATURDAY+1) weekdayRes(month) else weekdayRes(GregorianCalendar.SUNDAY))
        }


        for (i in 1..6) {
            for (j in 1..7) {
                val constraintLayout = LinearLayout(ctxt)

                val textView = AppCompatTextView(context)
                val cell = Cell(constraintLayout, textView)
                cells.add(cell)

                val lp = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f)).apply {
                        setGravity(Gravity.FILL)
                    }
                constraintLayout.layoutParams = lp
                constraintLayout.setBackgroundResource(R.drawable.day_cell_border)
                constraintLayout.gravity = Gravity.CENTER_HORIZONTAL
                constraintLayout.setOnClickListener{onCellClick(cell.dayModel)}
                constraintLayout.addView(textView)
                addView(constraintLayout)
            }
        }
    }

    private fun onCellClick(dayModel: DayModel?) {
        Log.d(PlanneratorApplication.appName, "clicked")
        if (dayModel != null)
            dayClickListener?.invoke(dayModel)
    }

    fun updateData(prevMonth : MonthModel, currMonth: MonthModel, nextMonth: MonthModel) {
        var startIndex = currMonth.firstDay.dayOfWeek
        // remap indices
        startIndex  -= 2
        if (startIndex  < 0)
            startIndex  = 6 + (startIndex + 1)

        val daysFromPrevMonth = startIndex - 1

        var currIndex = 0
        for (i in prevMonth.days.lastIndex - daysFromPrevMonth..prevMonth.days.lastIndex) {
            cells[currIndex++].setData(prevMonth.days[i], true, context)
        }

        for (d in currMonth.days) {
            cells[currIndex++].setData(d, false, context)
        }

        // 42 for 7*6 elements in grid
        for ((i, j) in (currIndex until 42).withIndex()) {
            cells[j].setData(nextMonth.days[i], true, context)
        }

    }

    class Cell(val layout: ViewGroup, val textView: AppCompatTextView, var dayModel: DayModel? = null) {
        fun setData(dayModel: DayModel, isGreyed: Boolean, ctxt: Context) {
            this.dayModel = dayModel
            textView.text = dayModel.date.get(GregorianCalendar.DAY_OF_MONTH).toString()

            if (isGreyed)
                textView.alpha = .2f
            else
                textView.alpha = 1.0f

            // decorate not empty days
            if (dayModel.events.size > 0)
                layout.setBackgroundColor(ctxt.getColor(R.color.highlightDay))
            else
                layout.setBackgroundResource(0)

            // decorate today
            if ((GregorianCalendar.getInstance() as GregorianCalendar).isSameDay(dayModel.date))
                textView.background = ctxt.getDrawable(R.drawable.circle)
            else
                textView.setBackgroundResource(0)

        }
    }
}