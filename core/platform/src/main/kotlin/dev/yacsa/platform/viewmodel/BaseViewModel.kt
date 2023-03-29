package dev.yacsa.platform.viewmodel

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import logcat.logcat
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val SAVED_UI_STATE_KEY = "savedUiStateKey"


abstract class BaseViewModel<UI_STATE : Parcelable, PARTIAL_UI_STATE, EVENT, INTENT>(
    savedStateHandle: SavedStateHandle,
    initialState: UI_STATE
) : ViewModel() {

    private val intentFlow = MutableSharedFlow<INTENT>()

    val uiState = savedStateHandle.getStateFlow(SAVED_UI_STATE_KEY, initialState)

    private val eventChannel = Channel<EVENT>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            intentFlow
                .flatMapMerge { mapIntents(it) }
                .scan(uiState.value, ::reduceUiState)
                .catch {
                    logcat { it.stackTraceToString() }
                }
                .collect {
                    savedStateHandle[SAVED_UI_STATE_KEY] = it
                }
        }
    }

    fun acceptIntent(intent: INTENT) =
        viewModelScope.launch {
            intentFlow.emit(intent)
        }

    protected fun publishEvent(event: EVENT) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }

    protected abstract fun mapIntents(intent: INTENT): Flow<PARTIAL_UI_STATE>

    protected abstract fun reduceUiState(
        previousState: UI_STATE,
        partialState: PARTIAL_UI_STATE
    ): UI_STATE


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

}