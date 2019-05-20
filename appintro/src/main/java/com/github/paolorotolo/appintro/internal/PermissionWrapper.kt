package com.github.paolorotolo.appintro.internal

/**
 * A data class that represents a set of permissions that should be requested to the user.
 * @property permissions An Array of Permissions from the Android Framework
 * @property position The position in the AppIntro pager when to request those permissions.
 * @property pending Flag set to true if AppIntro already requested this permission and is awaiting response.
 */
internal data class PermissionWrapper(
        var permissions: Array<String>,
        var position: Int,
        internal var pending: Boolean = false
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PermissionWrapper

        if (!permissions.contentEquals(other.permissions)) return false
        if (position != other.position) return false
        if (pending != other.pending) return false

        return true
    }

    override fun hashCode(): Int {
        var result = permissions.contentHashCode()
        result = 31 * result + position
        result = 31 * result + pending.hashCode()
        return result
    }
}
