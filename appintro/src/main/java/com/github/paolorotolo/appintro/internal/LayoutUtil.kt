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
       var isRTL : Boolean
        isRTL = false //RTL is supported only on API 17, so it will be false on API<17
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isRTL = ctx.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
        }
        return isRTL
    }
}
