package dev.yacsa.platform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import logcat.logcat
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    var errorStateFlow: StateFlow<Exception?> = MutableStateFlow(null)
    var loadingStateFlow: StateFlow<Boolean?> = MutableStateFlow(null)

    fun <T> launch(
        context: CoroutineContext = Dispatchers.IO,
        scope: CoroutineScope = viewModelScope,
        loading: StateFlow<Boolean?> = loadingStateFlow,
        error: StateFlow<Exception?> = errorStateFlow,
        result: StateFlow<T>? = null,
        request: suspend CoroutineScope.() -> T?
    ): Job = scope.launch {
        try {
            (loading as? MutableStateFlow)?.value = true
            withContext(context) {
                request()
            }.apply {
                this?.let {
                    (result as? MutableStateFlow<T>?)?.value = it
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            (loading as? MutableStateFlow)?.value = false
            (error as? MutableStateFlow)?.value = ex
        } finally {
            (loading as? MutableStateFlow)?.value = false
        }
    }


    sealed class UiState {
        object LoadingUiState
        class ErrorUiState()
        class ContentReadyUiState<T>(content: T?)
    }

}