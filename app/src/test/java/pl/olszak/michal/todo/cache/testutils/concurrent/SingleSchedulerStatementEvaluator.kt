package pl.olszak.michal.todo.cache.testutils.concurrent

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.runners.model.Statement

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class SingleSchedulerStatementEvaluator(private val scheduler: Scheduler, private val statement: Statement) : Statement() {

    @Throws(Throwable::class)
    override fun evaluate() {
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }

        try {
            statement.evaluate()
        } finally {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }
}