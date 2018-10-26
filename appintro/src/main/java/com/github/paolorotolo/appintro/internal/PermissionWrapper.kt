package com.github.paolorotolo.appintro.internal

import java.util.*

/**
 * A data class that represents a set of permissions that should be requested to the user.
 * @property permissions An Array of Permissions from the Android Framework
 * @property position The position in the AppIntro pager when to request those permissions.
 */
internal data class PermissionWrapper(
        var permissions: Array<String>,
        var position: Int) {

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
}
