package com.github.paolorotolo.appintro.internal

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * A data class that represents a set of permissions that should be requested to the user.
 * @property permissions An Array of Permissions from the Android Framework
 * @property position The position in the AppIntro pager when to request those permissions.
 * @property required Whether the permission being requested is required to move forward.
 */
internal data class PermissionWrapper(
        var permissions: Array<String>,
        var position: Int, var required: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.createStringArray(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PermissionWrapper

        if (!Arrays.equals(permissions, other.permissions)) return false
        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(permissions)
        result = 31 * result + position
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(permissions)
        parcel.writeInt(position)
        parcel.writeByte(if (required) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PermissionWrapper> {
        override fun createFromParcel(parcel: Parcel): PermissionWrapper {
            return PermissionWrapper(parcel)
        }

        override fun newArray(size: Int): Array<PermissionWrapper?> {
            return arrayOfNulls(size)
        }
    }

}
