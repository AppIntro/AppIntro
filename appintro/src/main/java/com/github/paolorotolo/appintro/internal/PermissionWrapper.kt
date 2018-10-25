package com.github.paolorotolo.appintro.internal

import java.util.*

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
