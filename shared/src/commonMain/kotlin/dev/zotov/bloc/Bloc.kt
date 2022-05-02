package dev.zotov.bloc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

/**
 * A [Bloc] is a class which extends [BlocBase] and can be extended to manage any type of state.
 * Differs from [ProxyBloc] in that it contains [stateFlow] inside itself
 */
abstract class Bloc<T>(initialState: T) : BlocBase() {

    /**
     * Flow in which the current state of the [Bloc] is located.
     */
    @Suppress("unused")
    protected val stateFlow: MutableStateFlow<T> = MutableStateFlow(initialState)

    /**
     * Current state of [Bloc].
     * You can subscribe to this flow where you want to listen state updates.
     * Should be modified only by [stateFlow]
     */
    val state: StateFlow<T> get() = stateFlow.asStateFlow()

    /**
     * ##### Should be used only on IOS
     *
     * Callback, which used to provide new [Bloc] state on IOS.
     * Should be used inside the ObservableObject to update its state when the [Bloc] state is updated
     */
    @Suppress("unused")
    fun onChange(provideNewState: BlocStateCollector<T>): Closeable {
        val job = Job()

        stateFlow
            .onEach { provideNewState.invoke(it) }
            .launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}