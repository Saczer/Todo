@file:Suppress("unused")

package pl.olszak.michal.todo.util.extension

import timber.log.Timber

/**
 * @author molszak
 *         created on 29.01.2018.
 */

/*
 * Adapted from [Slimber](https://github.com/PaulWoitaschek/Slimber/blob/bea76b32563906edc8cf196ce4b6cfce8d12d6e6/slimber/src/main/kotlin/de/paul_woitaschek/slimber/Slimber.kt)
 */

inline fun ifPlanted(action: () -> Unit) {
    if (Timber.treeCount() != 0) {
        action()
    }
}

inline fun logE(message: () -> String) = ifPlanted { Timber.e(message()) }

inline fun logE(throwable: Throwable, message: () -> String) = ifPlanted {
    Timber.e(throwable, message())
}

fun logE(throwable: Throwable) = ifPlanted { Timber.e(throwable) }