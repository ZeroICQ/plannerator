package com.github.zeroicq.plannerator.ui.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker

class DatePickerFragment : DialogFragment() {
    internal lateinit var listener: DatePickerDialogListener

    interface DatePickerDialogListener {
        fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //todo: get date from args
        return activity?.let { DatePickerDialog(it,
            DatePickerDialog.OnDateSetListener {datePicker, year, month, dayOfMonth -> listener.onDateSet(datePicker, year, month, dayOfMonth) },
            2019,
            2,
            2)
        }!!
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as DatePickerDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

}