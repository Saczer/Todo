package pl.olszak.michal.todo.testutils.concurrent

import io.reactivex.Scheduler
import io.reactivex.internal.schedulers.ExecutorScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

/**
 * @author molszak
 *         created on 26.01.2018.
 */
class ImmediateExecutionRule : TestRule {

    private val immediate = object : Scheduler() {
        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
        }
    }

    @Throws(IllegalArgumentException::class)
    override fun apply(base: Statement?, description: Description?): Statement {
        base?.let {
            return SingleSchedulerStatementEvaluator(immediate, it)
        }
        throw IllegalArgumentException("base should not be null")
    }
}