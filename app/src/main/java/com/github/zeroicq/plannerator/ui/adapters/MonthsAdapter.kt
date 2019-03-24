package com.github.zeroicq.plannerator.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.zeroicq.plannerator.mvp.models.MonthModel
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import com.github.zeroicq.plannerator.ui.customViews.MonthView

class MonthsAdapter(val monthPreseneter : MonthPresenter): RecyclerView.Adapter<MonthsAdapter.MonthHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MonthHolder {
//        val inflater = LayoutInflater.from(parent.context)
        val v = MonthView(parent.context)
        return MonthHolder(v)
    }

    override fun getItemCount() = monthPreseneter.loadedMonths.size


    override fun onBindViewHolder(holder: MonthHolder, pos: Int) {
//        if (monthPreseneter.loadedMonths.size - pos <)
        val prevMonth = monthPreseneter.loadedMonths[pos - 1]
        val currMonth = monthPreseneter.loadedMonths[pos]
        val nextMonth = monthPreseneter.loadedMonths[pos + 1]
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