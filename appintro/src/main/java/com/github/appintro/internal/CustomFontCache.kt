package com.github.appintro.internal

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat

/**
 * Custom Font Cache Implementation.
 * Prevent(s) memory leaks due to Typeface objects
 */
internal object CustomFontCache {

    private val TAG = LogHelper.makeLogTag(CustomFontCache::class)
    private val cache = hashMapOf<String, Typeface>()

    fun getFont(ctx: Context, path: String?, fontCallback: ResourcesCompat.FontCallback) {
        if (path.isNullOrEmpty()) {
            LogHelper.w(TAG, "Empty typeface path provided!")
            return
        }

        cache[path]?.let {
            // Cache hit! Return the typeface.
            fontCallback.onFontRetrieved(it)
        } ?: run {
            // Cache miss! Create the typeface and store it.
            val newTypeface = Typeface.createFromAsset(ctx.assets, path)
            cache[path] = newTypeface
            fontCallback.onFontRetrieved(newTypeface)
        }
    }
}
