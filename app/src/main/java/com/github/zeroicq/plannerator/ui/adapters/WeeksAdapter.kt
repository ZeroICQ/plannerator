package com.github.zeroicq.plannerator.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.zeroicq.plannerator.mvp.models.WeekModel
import com.github.zeroicq.plannerator.mvp.presenters.WeekPresenter
import com.github.zeroicq.plannerator.ui.customViews.WeekView

class WeeksAdapter(val weekPreseneter : WeekPresenter): RecyclerView.Adapter<WeeksAdapter.WeekHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): WeekHolder {
        val v = WeekView(parent.context)
        return WeekHolder(v)
    }

    override fun getItemCount() = weekPreseneter.loadedWeeks.size

    override fun onBindViewHolder(holder: WeekHolder, pos: Int) {
        val currWeek = weekPreseneter.loadedWeeks[pos]

        holder.setWeek(currWeek)
    }

    class WeekHolder(v: WeekView) : RecyclerView.ViewHolder(v) {
//        var binding: MonthByDayLayoutManager = DataBindingUtil.bind(v)!!

        fun setWeek(currWeek: WeekModel) {
            (itemView as WeekView).updateData(currWeek)
        }
    }

}