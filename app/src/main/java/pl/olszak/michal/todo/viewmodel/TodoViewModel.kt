package pl.olszak.michal.todo.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable

/**
 * @author molszak
 *         created on 25.01.2018.
 */
abstract class TodoViewModel<out T : BaseObservable> : ViewModel() {

    abstract fun provideBindable(): T

}