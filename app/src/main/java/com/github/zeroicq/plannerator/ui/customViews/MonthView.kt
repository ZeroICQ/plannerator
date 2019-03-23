package com.github.zeroicq.plannerator.ui.customViews

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.view.Gravity
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow

class MonthView(ctxt: Context) : GridLayout(ctxt) {
//    private lateinit var table : TableLayout

    init {
        layoutParams = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.MATCH_PARENT)
        rowCount = 6
        columnCount = 7
//        orientation = VERTICAL
//        alignmentMode = ALIGN_BOUNDS

        setBackgroundColor(Color.CYAN)

//        var lp = GridLayout.LayoutParams(
//            GridLayout.spec(GridLayout.UNDEFINED, 2.0f),
//            GridLayout.spec(GridLayout.UNDEFINED, 1.0f)
//        )
//        val lp = GridLayout.LayoutParams().apply {
//            setGravity(Gravity.FILL)
//        }
//        lp.setGravity(Gravity.FILL)


        for (i in 1..6) {
            for (j in 1..7) {
                val constraintLayout = LinearLayout(ctxt)
//                constraintLayout.layoutParams = ConstraintLayout.LayoutParams(
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
//                    ConstraintLayout.LayoutParams.WRAP_CONTENT)

                val textView = AppCompatTextView(context).apply { text = (i*10+j).toString() }
                constraintLayout.setBackgroundColor(Color.rgb(i*31 % 255, j*21%255, 20))

//                constraintLayout.addView(textView)
//                constraintLayout.layoutParams = lp
//                addView(constraintLayout)

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