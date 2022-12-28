package com.example.fall

import android.os.Parcel
import android.os.Parcelable

data class thread(
    var judul: String?,
    var isiThread: String?,
    var thumbs_up: Int,
    var thumbs_down: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(judul)
        parcel.writeString(isiThread)
        parcel.writeInt(thumbs_up)
        parcel.writeInt(thumbs_down)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<thread> {
        override fun createFromParcel(parcel: Parcel): thread {
            return thread(parcel)
        }

        override fun newArray(size: Int): Array<thread?> {
            return arrayOfNulls(size)
        }
    }
}
