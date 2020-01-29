package com.github.appintro.internal

import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

/**
 * Class for containing the Typefaces that can be used with AppIntro.
 * Provide either a URL or a Resource. If you provide both, the URL will be ignored.
 *
 * @property typeFaceUrl A [String] which is an URL of a font found at in the /assets folder
 * @property typeFaceResource An [Int] which is a @FontRes
 */
internal data class TypefaceContainer(
    var typeFaceUrl: String? = null,
    @FontRes var typeFaceResource: Int = 0
) {

    /**
     * Applies typeface to a given TextView object.
     * If there is no typeface (either URL or resource) set, this method is a no-op.
     *
     * @param textView The [TextView] where the Typeface will be applied
     */
    fun applyTo(textView: TextView?) {
        if (textView == null || textView.context == null) {
            return
        }
        if (typeFaceUrl == null && typeFaceResource == 0) {
            return
        }

        // Callback to font retrieval
        val callback = object : ResourcesCompat.FontCallback() {
            override fun onFontRetrievalFailed(reason: Int) {
                // Don't be panic, just do nothing.
            }
            override fun onFontRetrieved(typeface: Typeface) {
                textView.typeface = typeface
            }
        }

        // We give priority to the FontRes here.
        if (typeFaceResource != 0) {
            ResourcesCompat.getFont(textView.context, typeFaceResource, callback, null)
        } else {
            CustomFontCache.getFont(textView.context, typeFaceUrl, callback)
        }
    }
}
