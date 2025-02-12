package com.gfc.pokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val coroutineExceptionHandler: CoroutineExceptionHandler,
) : ViewModel() {

    protected fun launchWithExceptionHandler(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler, block = block)
    }
}
