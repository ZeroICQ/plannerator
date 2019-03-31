package com.github.zeroicq.plannerator.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PagerSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.R
import com.github.zeroicq.plannerator.databinding.FragmentMonthBinding
import com.github.zeroicq.plannerator.mvp.presenters.MonthPresenter
import com.github.zeroicq.plannerator.mvp.views.MonthView
import com.github.zeroicq.plannerator.ui.adapters.MonthsAdapter
import com.github.zeroicq.plannerator.ui.layoutManagers.MonthByDayLayoutManager
import com.github.zeroicq.plannerator.ui.listeners.SnapChangeListener

class MonthFragment : MvpAppCompatFragment(), MonthView {
    private lateinit var binding: FragmentMonthBinding
//
    @InjectPresenter
    lateinit var presenter: MonthPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_month, container, false)
        //recycler
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.monthRecycler)
//
        binding.fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            presenter.onClick()
        }
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

    override fun setToolBarText(text: String) {
        binding.toolbarText.text = text
    }
}