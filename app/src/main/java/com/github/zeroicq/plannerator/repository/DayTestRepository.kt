package com.github.zeroicq.plannerator.repository

import android.icu.util.GregorianCalendar
import com.github.zeroicq.plannerator.PlanneratorApplication
import com.github.zeroicq.plannerator.mockup.StorageMockup
import com.github.zeroicq.plannerator.mvp.models.DayModel
import javax.inject.Inject

/**
 * @param date used for retrieving month
 */
class DayTestRepository: DayRepositoryInterface {
    @Inject
    lateinit var storage : StorageMockup

    init {
        PlanneratorApplication.graph.inject(this)
    }

    override fun getDayData(date: GregorianCalendar): DayModel {
        return DayModel(date, storage.getDayEvents(date))
    }

}