package com.github.paolorotolo.appintro.internal

import android.content.Context
import android.graphics.Typeface

/**
 * Custom Font Cache Implementation.
 * Prevent(s) memory leaks due to Typeface objects
 */
internal object CustomFontCache {

    private val TAG = LogHelper.makeLogTag(CustomFontCache::class)
    private val cache = hashMapOf<String, Typeface>()

    operator fun get(path: String?, ctx: Context): Typeface? {
        if (path.isNullOrEmpty()) {
            LogHelper.w(TAG, "Empty typeface path provided!")
            return null
        }

        val storedTypeface = cache[path]
        return if (storedTypeface != null) {
            // Cache hit! Return the typeface.
            storedTypeface
        } else {
            // Cache miss! Create the typeface and store it.
            val newTypeface = Typeface.createFromAsset(ctx.assets, path)
            cache[path!!] = newTypeface
            newTypeface
        }
    }
}
