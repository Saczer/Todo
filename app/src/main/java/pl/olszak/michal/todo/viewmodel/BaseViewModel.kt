package pl.olszak.michal.todo.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * @author molszak
 *         created on 22.02.2018.
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    abstract fun start()

    override fun onCleared() {
        disposables.clear()
    }
}