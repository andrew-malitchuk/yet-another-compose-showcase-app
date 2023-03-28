package dev.yacsa.books.screen.list

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getStartUpConfigureUseCase: GetStartUpConfigureUseCase
) : BaseViewModel() {

    var result: StateFlow<StartUpConfigureDomainModel?> = MutableStateFlow(null)

    fun test() {
        launch(
            result = result
        ) {
            getStartUpConfigureUseCase()
        }
    }

}