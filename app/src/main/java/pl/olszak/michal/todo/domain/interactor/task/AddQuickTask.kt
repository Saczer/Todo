package pl.olszak.michal.todo.domain.interactor.task

import io.reactivex.Completable
import pl.olszak.michal.todo.data.TaskStore
import pl.olszak.michal.todo.domain.concurent.TodoSchedulers
import pl.olszak.michal.todo.domain.interactor.CompletableUseCase
import javax.inject.Inject

/**
 * @author molszak
 *         created on 20.02.2018.
 */
class AddQuickTask @Inject constructor(
        private val taskStore: TaskStore,
        schedulers: TodoSchedulers) : CompletableUseCase<String>(schedulers) {

    override fun buildUseCaseCompletable(params: String?): Completable {
        params?.let {
            if(it.isEmpty()){
                //todo: create task

                return error()
            }
        }
        return error()
    }

    private fun error(): Completable = Completable.error(NullPointerException("Provided title should not be empty or null"))


}