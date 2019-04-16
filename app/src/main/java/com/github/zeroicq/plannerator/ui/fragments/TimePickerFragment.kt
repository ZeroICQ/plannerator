package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.github.zeroicq.plannerator.util.getGregorianCalendar

class TimePickerFragment : DialogFragment() {
    enum class INTENT_KEYS { HOUR_OF_DAY, MINUTE }
    enum class BUNDLE_KEYS { DATE }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getGregorianCalendar(BUNDLE_KEYS.DATE.toString()) ?: GregorianCalendar.getInstance()

        return TimePickerDialog(activity, {timePicker, hourOfDay, minute ->
            val intent = Intent()
            intent.putExtra(INTENT_KEYS.HOUR_OF_DAY.toString(), hourOfDay)
            intent.putExtra(INTENT_KEYS.MINUTE.toString(), minute)
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        },
            date.get(GregorianCalendar.HOUR_OF_DAY),
            date.get(GregorianCalendar.MINUTE),
            true)
    }
}