package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment

class TimePickerFragment : DialogFragment() {
    enum class INTENT_KEYS { HOUR_OF_DAY, MINUTE }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // todo: get time from args
        return TimePickerDialog(activity, {timePicker, hourOfDay, minute ->
            val intent = Intent()
            intent.putExtra(INTENT_KEYS.HOUR_OF_DAY.toString(), hourOfDay)
            intent.putExtra(INTENT_KEYS.MINUTE.toString(), minute)
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        },
            2, 2, true)
    }
}