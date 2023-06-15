package dev.yacsa.platform

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.model.theme.ThemeDomainModel
import dev.yacsa.domain.usecase.theme.GetThemeUseCase
import dev.yacsa.domain.usecase.theme.SetThemeUseCase
import dev.yacsa.model.model.theme.ThemeUiModel
import javax.inject.Inject
import javax.inject.Singleton

interface Theme {
    var isDarkMode: MutableState<Boolean>
    var currentTheme: MutableState<ThemeUiModel?>
    suspend fun getTheme()
    suspend fun setTheme(value: ThemeUiModel)
}

open class ThemeDelegate @Inject constructor(
    private var getThemeUseCase: GetThemeUseCase,
    private var setThemeUseCase: SetThemeUseCase
) : Theme {

    override var isDarkMode: MutableState<Boolean> = mutableStateOf(false)
    override var currentTheme: MutableState<ThemeUiModel?> = mutableStateOf(null)

    override suspend fun getTheme() {
        getThemeUseCase().fold({
            it.toString()
        },{
            isDarkMode.value = it.name == "DARK"
            currentTheme.value = ThemeUiModel.valueOf(it.name)
//            true
        })
//        getThemeUseCase().isRight {
//            isDarkMode.value = it.name == "DARK"
//            currentTheme.value = ThemeUiModel.valueOf(it.name)
//            true
//        }
    }

    override suspend fun setTheme(themeUiModel: ThemeUiModel) {
        setThemeUseCase(ThemeDomainModel.valueOf(themeUiModel.name))
        getThemeUseCase()
        isDarkMode.value = themeUiModel.name == "DARK"
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class FooModule {

    @Singleton
    @Binds
    abstract fun bindsFoo(
        themeDelegate: ThemeDelegate,
    ): Theme

}