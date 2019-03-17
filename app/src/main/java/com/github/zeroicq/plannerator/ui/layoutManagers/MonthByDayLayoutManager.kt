package com.github.zeroicq.plannerator.ui.layoutManagers

import android.content.Context
import android.support.v7.widget.LinearLayoutManager

class MonthByDayLayoutManager(ctxt: Context) : LinearLayoutManager(ctxt, HORIZONTAL, false) {
    init {
        initialPrefetchItemCount = 4
    }
}