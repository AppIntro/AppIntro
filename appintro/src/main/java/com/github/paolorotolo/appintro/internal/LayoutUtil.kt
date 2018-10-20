package com.github.paolorotolo.appintro.internal

import android.content.Context
import android.os.Build
import android.view.View

/**
 * Util object for interacting with Layouts
 */
internal object LayoutUtil {

    @JvmStatic
    fun isRtl(ctx: Context): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 &&
                ctx.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
    }
}
