package com.github.appintro.internal

import android.content.Context
import android.view.View

/**
 * Util object for interacting with Layouts
 */
internal object LayoutUtil {
    @JvmStatic
    fun isRtl(ctx: Context): Boolean {
        return ctx.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
    }
}
