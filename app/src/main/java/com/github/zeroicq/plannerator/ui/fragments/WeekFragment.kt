package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentWeekBinding
import com.github.zeroicq.plannerator.mvp.presenters.WeekPresenter
import com.github.zeroicq.plannerator.mvp.views.WeekView
import com.github.zeroicq.plannerator.ui.adapters.WeeksAdapter
import com.github.zeroicq.plannerator.ui.layoutManagers.MonthByDayLayoutManager
import com.github.zeroicq.plannerator.ui.listeners.SnapChangeListener

class WeekFragment : MvpAppCompatFragment(), WeekView {

    private lateinit var binding: FragmentWeekBinding
    //
    @InjectPresenter
    lateinit var presenter: WeekPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_week, container, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.weekRecycler)
        // recycler
        binding.weekRecycler.apply {
            adapter = WeeksAdapter(presenter)
            layoutManager = MonthByDayLayoutManager(this.context).apply {
                addOnScrollListener(SnapChangeListener(snapHelper) {
                    Log.d(PlanneratorApplication.appName, "showing ${presenter.loadedWeeks[it+1].date.time}")
                    presenter.onMonthPosChange(it)
                })
            }
            scrollToPosition(presenter.curWeekPos - 1)
        }

        return binding.root
    }

    override fun onRecyclerAdvance(amount: Int) {
        binding.weekRecycler.adapter?.notifyItemRangeInserted(presenter.loadedWeeks.lastIndex, amount)
        binding.weekRecycler.adapter?.notifyItemRangeRemoved(0, amount)
    }

    override fun onRecyclerPrev(amount: Int) {
        binding.weekRecycler.adapter?.notifyItemRangeRemoved(presenter.loadedWeeks.size - amount, amount)
        binding.weekRecycler.adapter?.notifyItemRangeInserted(0, amount)
    }
}
