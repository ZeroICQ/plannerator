package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.icu.util.GregorianCalendar
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
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
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.util.weekdayRes

class WeekView(ctxt: Context) : LinearLayoutCompat(ctxt) {
    private val daysOfWeekTitles: GridLayout

    init {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        orientation = VERTICAL

        // titles
        daysOfWeekTitles = GridLayout(ctxt)
        daysOfWeekTitles.rowCount = 1
        daysOfWeekTitles.columnCount = 8

        daysOfWeekTitles.layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT)

        addDayOfWeekTitle(ctxt, null)
        for (month in GregorianCalendar.MONDAY..GregorianCalendar.SATURDAY + 1) {
            addDayOfWeekTitle(ctxt, month)
        }

        addView(daysOfWeekTitles)
        // hour table
        val scrollView = ScrollView(ctxt)
        scrollView.overScrollMode = View.OVER_SCROLL_NEVER
        scrollView.isVerticalScrollBarEnabled = false

        scrollView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

//        scrollView.setBackgroundColor(Color.GRAY)
        scrollView.isFillViewport = true


        val gridView = GridLayout(ctxt)
        gridView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)

        gridView.columnCount = 8
        gridView.rowCount = 24
//        gridView.setBackgroundColor(Color.RED)

        for (row in 1..24) {
            for (col in 1..8) {
                val hourCellLayout = LinearLayout(ctxt)
//                constraintLayout.setOnClickListener{onCellClick(it)}
                hourCellLayout.setBackgroundResource(R.drawable.day_cell_border)
                hourCellLayout.minimumHeight = 200
                if (col == 1 && row != 24) {
                    hourCellLayout.gravity = Gravity.END or Gravity.BOTTOM
                    hourCellLayout.setPadding(0, 0, 5, 0)
                    val hourTitleTextView = AppCompatTextView(ctxt).apply {
                        width = 150
                        text = "${row}:00"
                    }
                    hourTitleTextView.gravity = Gravity.END
                    hourCellLayout.addView(hourTitleTextView)
                }
                else {
                    val lp = GridLayout.LayoutParams(
                        GridLayout.spec(GridLayout.UNDEFINED, 1.0f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1.0f))

                    hourCellLayout.gravity = Gravity.FILL_HORIZONTAL
                    hourCellLayout.layoutParams = lp

                    hourCellLayout.gravity = Gravity.FILL_HORIZONTAL
                }
//                val textView = AppCompatTextView(context)
//                textView.text = "tst"
////                cells.add(Cell(textView))
//
//                hourCellLayout.addView(textView)
                gridView.addView(hourCellLayout)
            }
        }
        scrollView.addView(gridView)
        addView(scrollView)
    }

    private fun addDayOfWeekTitle(ctxt: Context, month: Int?) {
        val dayOfWeekLayout = LinearLayout(ctxt)
        val dayOfWeekTextView = AppCompatTextView(context)


//        dayOfWeekLayout.setBackgroundResource(R.drawable.day_cell_border)

        dayOfWeekLayout.addView(dayOfWeekTextView)
        if (month == null)
            dayOfWeekTextView.width = 150
        else {
            val lp = GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 0.1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1.0f)).apply {
                setGravity(Gravity.CENTER or Gravity.BOTTOM)
            }
            dayOfWeekLayout.layoutParams = lp

            dayOfWeekTextView.setText( if (month != GregorianCalendar.SATURDAY+1) weekdayRes(month) else weekdayRes(GregorianCalendar.SUNDAY))
        }

        daysOfWeekTitles.addView(dayOfWeekLayout)
    }

    private fun onCellClick(v: View) {
//        v.setBackgroundColor(Color.CYAN)
        Log.d(PlanneratorApplication.appName, "clicked")
    }

    fun updateData(currWeek: WeekModel) {
//        var startIndex = currMonth.firstDay.dayOfWeek
//        // remap indices
//        startIndex  -= 2
//        if (startIndex  < 0)
//            startIndex  = 6 + (startIndex + 1)
//
//        val daysFromPrevMonth = startIndex - 1
//
//        var currIndex = 0
//        for (i in prevMonth.days.lastIndex - daysFromPrevMonth..prevMonth.days.lastIndex) {
//            cells[currIndex++].setData(prevMonth.days[i], true)
//        }
//
//        for (d in currMonth.days) {
//            cells[currIndex++].setData(d, false)
//        }
//
//        // 42 for 7*6 elements in grid
//        for ((i, j) in (currIndex until 7).withIndex()) {
//            cells[j].setData(nextMonth.days[i], true)
//        }
    }

    class Cell(val textView: AppCompatTextView) {
        fun setData(dayModel: DayModel, isGreyed: Boolean) {
            textView.text = dayModel.date.get(GregorianCalendar.DAY_OF_MONTH).toString()

            if (isGreyed)
                textView.alpha = .2f
            else
                textView.alpha = 1.0f
        }
    }
}