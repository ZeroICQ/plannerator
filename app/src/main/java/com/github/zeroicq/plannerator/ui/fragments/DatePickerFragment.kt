package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.github.zeroicq.plannerator.util.getGregorianCalendar

class DatePickerFragment : DialogFragment() {
    enum class INTENT_KEYS { YEAR, MONTH, DAY_OF_MONTH }
    enum class BUNDLE_KEYS { DATE }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getGregorianCalendar(BUNDLE_KEYS.DATE.toString()) ?: GregorianCalendar.getInstance()

        return DatePickerDialog(activity, {view, year, month, dayOfMonth ->
            val intent = Intent()
            intent.putExtra(INTENT_KEYS.YEAR.toString(), year)
            intent.putExtra(INTENT_KEYS.MONTH.toString(), month)
            intent.putExtra(INTENT_KEYS.DAY_OF_MONTH.toString(), dayOfMonth)
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        },
            date.get(GregorianCalendar.YEAR),
            date.get(GregorianCalendar.MONTH),
            date.get(GregorianCalendar.DAY_OF_MONTH))
    }

}