package pl.olszak.michal.todo.data.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import org.threeten.bp.Instant
import pl.olszak.michal.todo.BR
import pl.olszak.michal.todo.viewmodel.Binding

/**
 * Created by molszak.
 * 20.01.2018
 */
open class Task(val id: Long) : BaseObservable(), Binding {

    @Bindable
    var title: String = ""
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.title)
            }
        }

    @Bindable
    var description: String? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.description)
            }
        }

    @Bindable
    var done: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.done)
            }
        }

    @Bindable
    var priority: Priority = Priority.LOW
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.priority)
            }
        }

    @Bindable
    var icon: Icon = Icon.NONE
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.icon)
            }
        }

    @Bindable
    var time: Instant? = null
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.time)
            }
        }


}