package com.example.rickandmorty.viewmodels

import android.util.Log
import com.example.rickandmorty.ui.character.TAG
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(launchBody: suspend () -> Unit): Job {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "VM safeLaunch error: $throwable")
        throwable.printStackTrace()
    }

    return this.launch(coroutineExceptionHandler) {
        launchBody.invoke()
    }
}