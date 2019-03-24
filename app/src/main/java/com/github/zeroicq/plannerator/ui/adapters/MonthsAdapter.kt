package com.github.zeroicq.plannerator.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import com.github.zeroicq.plannerator.ui.customViews.MonthView
import java.util.*

class MonthsAdapter(val monthPreseneter : MonthPresenter): RecyclerView.Adapter<MonthsAdapter.MonthHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MonthHolder {
//        val inflater = LayoutInflater.from(parent.context)
        val v = MonthView(parent.context)
        return MonthHolder(v)
    }

    override fun getItemCount() = monthPreseneter.loadedMonths.size - 2


    override fun onBindViewHolder(holder: MonthHolder, pos: Int) {
//        if (monthPreseneter.loadedMonths.size - pos <)

        val prevMonth = monthPreseneter.loadedMonths[pos]
        val currMonth = monthPreseneter.loadedMonths[pos+1]
        val nextMonth = monthPreseneter.loadedMonths[pos+2]
//        if (currMonth.date.get(GregorianCalendar.MONTH) == GregorianCalendar.JUNE) {
//            Log.d("s", "s")
//        }
        holder.setMonths(prevMonth, currMonth, nextMonth)
//        notifyItemChanged(pos)
    }

    class MonthHolder(v: MonthView) : RecyclerView.ViewHolder(v) {
//        var binding: MonthByDayLayoutManager = DataBindingUtil.bind(v)!!

        fun setMonths(prevMonth : MonthModel, currMonth: MonthModel, nextMonth: MonthModel) {
            (itemView as MonthView).updateData(prevMonth, currMonth, nextMonth)
        }
    }

}