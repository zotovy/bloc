package dev.zotov.bloc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class ProxyBloc<T>: BlocBase() {

    /**
     * A current state of [ProxyBloc]
     */
    abstract val state: Flow<T>

    /**
     * ##### Should be used only on IOS
     *
     * Callback, which used to provide new [Bloc] state on IOS.
     * Should be used inside the ObservableObject to update its state when the [Bloc] state is updated
     */
    @Suppress("unused")
    fun onChange(provideNewState: BlocStateCollector<T>): Closeable {
        val job = Job()

        state
            .onEach { provideNewState.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}