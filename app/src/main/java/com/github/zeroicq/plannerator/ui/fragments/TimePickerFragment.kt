package com.github.zeroicq.plannerator.ui.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class TimePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(activity, {timePicker, i, a ->  }, 2, 2, true)
    }
}