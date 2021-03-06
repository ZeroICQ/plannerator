package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentMonthBinding
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import com.github.zeroicq.plannerator.mvp.views.MonthView
import com.github.zeroicq.plannerator.ui.adapters.MonthsAdapter
import com.github.zeroicq.plannerator.ui.layoutManagers.MonthByDayLayoutManager
import com.github.zeroicq.plannerator.ui.listeners.SnapChangeListener


class MonthFragment : BaseFragment(), MonthView {
    private lateinit var binding: FragmentMonthBinding
//
    @InjectPresenter
    lateinit var presenter: MonthPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        showFab = true
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_month, container, false)
        //recycler
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.monthRecycler)
        activityView.setFabOnclickListener { view -> presenter.onCreateEvent() }
//
        binding.monthRecycler.apply {
            adapter = MonthsAdapter(presenter)
            layoutManager = MonthByDayLayoutManager(this.context).apply {
                addOnScrollListener(SnapChangeListener(snapHelper) {
                    Log.d(PlanneratorApplication.appName, "showing ${presenter.loadedMonths[it+1].date.time}")
                    presenter.onMonthPosChange(it)
                })
            }
            scrollToPosition(presenter.curMonthPos - 1)
        }
//
        return binding.root
    }

    override fun onRecyclerAdvance(amount: Int) {
        binding.monthRecycler.adapter?.notifyItemRangeInserted(presenter.loadedMonths.lastIndex, amount)
        binding.monthRecycler.adapter?.notifyItemRangeRemoved(0, amount)
    }

    override fun onRecyclerPrev(amount: Int) {
        binding.monthRecycler.adapter?.notifyItemRangeRemoved(presenter.loadedMonths.size-amount, amount)
        binding.monthRecycler.adapter?.notifyItemRangeInserted(0, amount)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadMonths()
    }
}
