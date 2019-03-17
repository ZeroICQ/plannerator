package com.github.zeroicq.plannerator.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.zeroicq.plannerator.databinding.ActivityMonthBinding
import com.github.zeroicq.plannerator.ui.customViews.MonthView

class MonthsAdapter: RecyclerView.Adapter<MonthsAdapter.MonthHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MonthHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = MonthView(parent.context)
//        parent.addView(v)
        return MonthHolder(v)
    }

    override fun getItemCount(): Int {
        return 4
    }


    override fun onBindViewHolder(holder: MonthHolder, pos: Int) {
    }

    class MonthHolder(v: View) : RecyclerView.ViewHolder(v) {
//        var binding: MonthLayoutBinding = DataBindingUtil.bind(v)!!
    }

}