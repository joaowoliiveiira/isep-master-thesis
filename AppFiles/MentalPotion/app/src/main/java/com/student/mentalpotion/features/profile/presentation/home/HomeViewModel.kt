package com.student.mentalpotion.features.profile.presentation.home

import androidx.lifecycle.ViewModel
import com.student.mentalpotion.features.authentication.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() {
        logoutUseCase()
    }

    private val _selectedTab = MutableStateFlow("overall")
    val selectedTab: StateFlow<String> = _selectedTab.asStateFlow()

    private val _selectedTool = MutableStateFlow<Int?>(null)
    val selectedTool: StateFlow<Int?> = _selectedTool.asStateFlow()

    fun setSelectedTab(tab: String) {
        _selectedTab.value = tab
    }

    fun setSelectedTool(toolIndex: Int?) {
        _selectedTool.value = toolIndex
    }

    fun onNextActivityClick() {
        // TODO: Add logic when user clicks on the next activity card
    }

    fun onWeeklyRoutineClick() {
        // TODO: Add logic when user clicks on the weekly routine card
    }
}