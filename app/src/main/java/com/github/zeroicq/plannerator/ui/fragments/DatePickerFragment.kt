package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment

class DatePickerFragment : DialogFragment() {
    enum class INTENT_KEYS { YEAR, MONTH, DAY_OF_MONTH }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //todo: get date from args
        return DatePickerDialog(activity, {view, year, month, dayOfMonth ->
            val intent = Intent()
            intent.putExtra(INTENT_KEYS.YEAR.toString(), year)
            intent.putExtra(INTENT_KEYS.MONTH.toString(), month)
            intent.putExtra(INTENT_KEYS.DAY_OF_MONTH.toString(), dayOfMonth)
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        },
            2019, 2, 2)
    }

}