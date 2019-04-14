package com.github.zeroicq.plannerator.mvp.models

import android.icu.util.GregorianCalendar
import android.os.Parcel
import android.os.Parcelable

class EventModel (val date: GregorianCalendar, val title: String, val message: String = ""): Parcelable {

    // Parcelabel
    constructor(parcel: Parcel) : this(
        (GregorianCalendar.getInstance().apply {
            timeInMillis = parcel.readLong()
        } as GregorianCalendar),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(date.timeInMillis)
        parcel.writeString(title)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventModel> {
        override fun createFromParcel(parcel: Parcel): EventModel {
            return EventModel(parcel)
        }

        override fun newArray(size: Int): Array<EventModel?> {
            return arrayOfNulls(size)
        }
    }

}