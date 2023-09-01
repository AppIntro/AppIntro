package com.github.appintro

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.appintro.internal.delegate

internal const val ARG_TITLE = "title"
internal const val ARG_TITLE_TYPEFACE_URL = "title_typeface"
internal const val ARG_TITLE_TYPEFACE_RES = "title_typeface_res"
internal const val ARG_DESC = "desc"
internal const val ARG_DESC_TYPEFACE_URL = "desc_typeface"
internal const val ARG_DESC_TYPEFACE_RES = "desc_typeface_res"
internal const val ARG_DRAWABLE = "drawable"
internal const val ARG_BG_COLOR = "bg_color"
internal const val ARG_BG_COLOR_RES = "bg_color_res"
internal const val ARG_TITLE_COLOR = "title_color"
internal const val ARG_TITLE_COLOR_RES = "title_color_res"
internal const val ARG_DESC_COLOR = "desc_color"
internal const val ARG_DESC_COLOR_RES = "desc_color_res"
internal const val ARG_BG_DRAWABLE = "bg_drawable"

internal class AppIntroFragmentViewModel(state: SavedStateHandle) : ViewModel() {
    internal var title by state.delegate<CharSequence?>(ARG_TITLE)
    internal var description by state.delegate<CharSequence?>(ARG_DESC)
    internal var drawable by state.delegate<Int?>(ARG_DRAWABLE)
    internal var bgDrawable by state.delegate<Int?>(ARG_BG_DRAWABLE)

    @get:ColorInt
    internal var titleColor by state.delegate<Int?>(ARG_TITLE_COLOR)

    @get:ColorRes
    internal var titleColorRes by state.delegate<Int?>(ARG_TITLE_COLOR_RES)

    @get:ColorInt
    internal var descColor by state.delegate<Int?>(ARG_DESC_COLOR)

    @get:ColorRes
    internal var descColorRes by state.delegate<Int?>(ARG_DESC_COLOR_RES)

    @get:ColorRes
    internal var defaultBackgroundColorRes by state.delegate<Int?>(ARG_BG_COLOR_RES)

    @get:ColorInt
    internal var defaultBackgroundColor by state.delegate<Int?>(ARG_BG_COLOR)

    @get:FontRes
    internal var titleTypefaceRes by state.delegate<Int?>(ARG_TITLE_TYPEFACE_RES)
    internal var titleTypefaceUrl by state.delegate<String?>(ARG_TITLE_TYPEFACE_URL)

    @get:FontRes
    internal var descTypefaceRes by state.delegate<Int?>(ARG_DESC_TYPEFACE_RES)
    internal var descTypefaceUrl by state.delegate<String?>(ARG_DESC_TYPEFACE_URL)
}
