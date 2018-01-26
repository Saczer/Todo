package pl.olszak.michal.todo.data.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.Observable
import android.text.TextUtils
import pl.olszak.michal.todo.BR
import pl.olszak.michal.todo.viewmodel.Binding

/**
 * Created by molszak.
 * 20.01.2018
 */
open class TaskBinding(val id : Long): BaseObservable(), Binding {

    @Bindable
    var title: String = ""
        set(value) {
            if (!TextUtils.isEmpty(value)) {
                field = value
                notifyPropertyChanged(BR.title)
            }
        }

    @Bindable
    var description: String = ""
        set(value) {
            if (!TextUtils.isEmpty(value)) {
                field = value
                notifyPropertyChanged(BR.description)
            }
        }

    @Bindable
    var done : Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.done)
        }

    @Bindable
    var visible: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.visible)
        }

    @Bindable
    var parent: Observable? = null
        set(value) {
            field = value
            if (field != null) {
                notifyPropertyChanged(BR.parent)
            }
        }

    @Bindable
    fun getDeph(): Int {
        if (parent != null && parent is TaskBinding) {
            val parentTask: TaskBinding = parent as TaskBinding
            return parentTask.getDeph()
        }

        return 0
    }


}