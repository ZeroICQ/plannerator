package com.github.zeroicq.plannerator.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.mvp.presenters.WeekPresenter
import com.github.zeroicq.plannerator.ui.customViews.MonthView
import com.github.zeroicq.plannerator.ui.customViews.WeekView

class WeeksAdapter(val weekPreseneter : WeekPresenter): RecyclerView.Adapter<WeeksAdapter.WeekHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): WeekHolder {
//        val inflater = LayoutInflater.from(parent.context)
        val v = WeekView(parent.context)
        return WeekHolder(v)
    }

    override fun getItemCount() = weekPreseneter.loadedWeeks.size - 2

    override fun onBindViewHolder(holder: WeekHolder, pos: Int) {
//        if (monthPreseneter.loadedWeeks.size - pos <)

        val prevWeek = weekPreseneter.loadedWeeks[pos]
        val currWeek = weekPreseneter.loadedWeeks[pos+1]
        val nextWeek = weekPreseneter.loadedWeeks[pos+2]
//        if (currMonth.date.get(GregorianCalendar.MONTH) == GregorianCalendar.JUNE) {
//            Log.d("s", "s")
//        }
        holder.setWeeks(prevWeek, currWeek, nextWeek)
//        notifyItemChanged(pos)
    }

    class WeekHolder(v: WeekView) : RecyclerView.ViewHolder(v) {
//        var binding: MonthByDayLayoutManager = DataBindingUtil.bind(v)!!

        fun setWeeks(prevWeek : WeekModel, currWeek: WeekModel, nextWeek: WeekModel) {
            (itemView as WeekView).updateData(prevWeek, currWeek, nextWeek)
        }
    }

}