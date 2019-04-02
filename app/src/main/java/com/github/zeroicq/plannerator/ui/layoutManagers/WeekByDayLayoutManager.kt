package com.github.zeroicq.plannerator.ui.layoutManagers

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import javax.inject.Inject

class WeekByDayLayoutManager(ctxt: Context) : LinearLayoutManager(ctxt, HORIZONTAL, false) {
    init {
        // seems doing nothing
        initialPrefetchItemCount = 0
    }
}