package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow

class MonthView(ctxt: Context) : LinearLayout(ctxt) {
    private lateinit var table : TableLayout
    init {
        layoutParams = LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        orientation = LinearLayout.VERTICAL
        table = TableLayout(context)
        table.isStretchAllColumns = true
        table.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT)

        for (i in 1..6) {
            val tableRow = TableRow(context)
//            tableRow.setBackgroundColor(Color.CYAN)
            tableRow.layoutParams =
                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1.0f)

            tableRow.gravity = Gravity.CENTER


            for (j in 1..7) {
//                val constraintLayout = ConstraintLayout(ctxt)

                val textView = AppCompatTextView(context).apply { text = (i*10+j).toString() }
//                textView.setBackgroundColor(Color.rgb(i*100 % 255, j*10%255, 20))
                textView.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
                tableRow.addView(textView)
            }
            table.addView(tableRow)
        }
        addView(table)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        }


//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        for (i in 0 until childCount) {
//            val child = getChildAt(i)
//            child.layout(20, 20, 20, 20)
//        }
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}