package dev.zotov.bloc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

/**
 * An base class for the core functionality implemented by both [Bloc] and [ProxyBloc]
 */
abstract class BlocBase {

    /**
     * You can access the [CoroutineScope] of a [Bloc] through the [coroutineScope] property.
     *
     * A [coroutineScope] is defined for each [Bloc] in your app. Any coroutine launched in this
     * scope is automatically canceled if the [Bloc] is cleared. Coroutines are useful here for when
     * you have work that needs to be done only if the [Bloc] is active. For example, if you are
     * computing some data for a layout, you should scope the work to the [Bloc] so that if the
     * [Bloc] is cleared, the work is canceled automatically to avoid consuming resources.
     *
     */
    @Suppress("unused")
    protected val coroutineScope = CoroutineScope(Dispatchers.Main)

    /**
     * Clears the [Bloc] and cancels [coroutineScope]
     */
    fun dispose() {
        coroutineScope.cancel()
    }
}