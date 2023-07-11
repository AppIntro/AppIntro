package com.github.appintro.internal

import java.io.Serializable

/**
 * A data class that represents a set of permissions that should be requested to the user.
 * @property permissions An Array of Permissions from the Android Framework
 * @property position The position in the AppIntro pager when to request those permissions.
 * @property required Whether the permission being requested needs to be granted to move forward.
 */
internal data class PermissionWrapper(
    var permissions: Array<String>,
    var position: Int,
    var required: Boolean = true
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PermissionWrapper

        if (!permissions.contentEquals(other.permissions)) return false
        if (position != other.position) return false
        if (required != other.required) return false

        return true
    }

    override fun hashCode(): Int {
        var result = permissions.contentHashCode()
        result = 31 * result + position
        result = 31 * result + required.hashCode()
        return result
    }

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
