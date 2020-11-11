package com.enzoftware.composenews.utils


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

data class CoroutinesDispatchers(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
){
    @Inject
    constructor(): this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}