package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.icu.util.GregorianCalendar
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.widget.GridLayout
import android.widget.LinearLayout
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.mvp.models.DayModel
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.util.weekdayRes
import java.util.*

class MonthView(ctxt: Context) : GridLayout(ctxt) {
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
                rowCount = 1
                columnCount = i+1
//                height = ConstraintLayout.LayoutParams.WRAP_CONTENT
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
                cells.add(Cell(textView))

                val lp = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f)).apply {
                        setGravity(Gravity.FILL)
                        rowCount = i
                        columnCount = j
                }
                constraintLayout.layoutParams = lp
                constraintLayout.setBackgroundResource(R.drawable.day_cell_border)
                constraintLayout.gravity = Gravity.CENTER_HORIZONTAL

                constraintLayout.addView(textView)
                addView(constraintLayout)
            }
        }
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
            cells[currIndex++].setData(prevMonth.days[i], true)
        }

        for (d in currMonth.days) {
            cells[currIndex++].setData(d, false)
        }

        // 42 for 7*6 elements in grid
        for ((i, j) in (currIndex until 42).withIndex()) {
            cells[j].setData(nextMonth.days[i], true)
        }
    }

    class Cell(val textView: AppCompatTextView) {
        fun setData(dayModel: DayModel, isGreyed: Boolean) {
            textView.text = dayModel.date.get(GregorianCalendar.DAY_OF_MONTH).toString()
            if (isGreyed)
                textView.alpha = .2f
        }
    }
}