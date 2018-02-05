package pl.olszak.michal.todo.viewmodel.bindingadapter

import android.databinding.BindingAdapter
import pl.olszak.michal.todo.cache.model.ThemePalette
import pl.olszak.michal.todo.view.circle.ThemeGroup

/**
 * @author molszak
 *         created on 05.02.2018.
 */
@BindingAdapter(value = ["themePalette"], requireAll = false)
fun bindThemePalette(view: ThemeGroup, themePalette: ThemePalette?) {
    themePalette?.let {
        view.setTheme(themePalette)
    }
}

@BindingAdapter(value = ["themePaletteChange"], requireAll = false)
fun bindThemePaletteChange(view: ThemeGroup, listener: ThemeGroup.OnChangeTheme?) {
    listener?.let {
        if (view.onThemeChangeListener != null && view.onThemeChangeListener == it) {
            return
        }

        view.onThemeChangeListener = it
    }
}