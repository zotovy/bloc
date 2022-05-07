package dev.zotov.android

import dev.zotov.bloc.BlocBase
import dev.zotov.bloc.ProxyBloc
import dev.zotov.bloc.Bloc as CoreBloc
import dev.zotov.bloc.ProxyBloc as CoreProxyBloc

/**
 * A [Bloc] is a class which extends [BlocBase] and can be extended to manage any type of state.
 * Differs from [ProxyBloc] in that it contains [stateFlow] inside itself
 */
abstract class Bloc<T>(initialState: T): CoreBloc<T>(initialState)


abstract class ProxyBloc<T>: CoreProxyBloc<T>()