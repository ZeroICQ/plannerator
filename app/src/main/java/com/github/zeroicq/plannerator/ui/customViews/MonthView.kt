package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.widget.GridLayout
import android.widget.LinearLayout
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import java.util.*

class MonthView(ctxt: Context) : GridLayout(ctxt) {
    private val cells = ArrayList<Cell>()

    init {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        rowCount = 6
        columnCount = 7

        setBackgroundColor(Color.CYAN)

        for (i in 1..6) {
            for (j in 1..7) {
                val constraintLayout = LinearLayout(ctxt)

                val textView = AppCompatTextView(context)
                cells.add(Cell(textView))
                constraintLayout.setBackgroundColor(Color.rgb(i*31 % 255, j*21%255, 20))

                val lp = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1.0f)).apply {
                        setGravity(Gravity.FILL)
                        rowCount = i
                        columnCount = j
                }
                constraintLayout.layoutParams = lp

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
            startIndex  = 6 + (startIndex  + 1)

        val daysFromPrevMonth = startIndex  - 1

        var currIndex = 0
        for (i in prevMonth.days.lastIndex - daysFromPrevMonth..prevMonth.days.lastIndex) {
            cells[currIndex++].textView.text = prevMonth.days[i].message
        }

        for (d in currMonth.days) {
            cells[currIndex++].textView.text = d.message
        }

        // 42 for 7*6 elements in grid
        for ((i, j) in (currIndex until 42).withIndex()) {
            cells[j].textView.text = nextMonth.days[i].message
        }
    }

    class Cell(val textView: AppCompatTextView)
}