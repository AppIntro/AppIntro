package com.github.paolorotolo.appintro.internal

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
        if (textView == null || textView.context == null
                || (typeFaceUrl == null && typeFaceResource == 0)) {
            return
        }

        // We give priority to the FontRes here.
        val textTypeface: Typeface? = if (typeFaceResource != 0)
            ResourcesCompat.getFont(textView.context, typeFaceResource)
        else
            CustomFontCache[typeFaceUrl, textView.context]

        if (textTypeface != null)
            textView.typeface = textTypeface
    }
}
