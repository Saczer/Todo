package pl.olszak.michal.todo.util.animation.model

import android.os.Parcel
import android.os.Parcelable

/**
 * @author molszak
 *         created on 06.02.2018.
 */
data class RevealAnimationSetting(val centerX : Int, val centerY : Int, val width : Int, val height : Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(centerX)
        parcel.writeInt(centerY)
        parcel.writeInt(width)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RevealAnimationSetting> {
        override fun createFromParcel(parcel: Parcel): RevealAnimationSetting {
            return RevealAnimationSetting(parcel)
        }

        override fun newArray(size: Int): Array<RevealAnimationSetting?> {
            return arrayOfNulls(size)
        }
    }
}