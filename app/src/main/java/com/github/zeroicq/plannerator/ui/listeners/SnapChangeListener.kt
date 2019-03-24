package com.github.zeroicq.plannerator.ui.listeners

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper


fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

class SnapChangeListener(val snapHelper: SnapHelper, val onSnapPositionChangeListener: (Int) -> Unit)
    : RecyclerView.OnScrollListener()
{
    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        maybeNotifySnapPositionChange(recyclerView)
    }

    private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChangeListener(snapPosition)
            this.snapPosition = snapPosition
        }
    }
}