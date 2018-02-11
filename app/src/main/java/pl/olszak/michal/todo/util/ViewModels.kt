package pl.olszak.michal.todo.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Created by molszak.
 * 11.02.2018
 */

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
        provider: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, provider).get(VM::class.java)
}

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(provider: ViewModelProvider.Factory): VM {
    return ViewModelProviders.of(this, provider).get(VM::class.java)
}