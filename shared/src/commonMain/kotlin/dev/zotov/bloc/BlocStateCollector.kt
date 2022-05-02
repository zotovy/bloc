package dev.zotov.bloc


/**
 * Used to subscribe to [Bloc] on IOS
 */
fun interface BlocStateCollector<in T> {

    /**
     * Used to subscribe to [Bloc] on IOS
     */
    fun invoke(value: T)
}