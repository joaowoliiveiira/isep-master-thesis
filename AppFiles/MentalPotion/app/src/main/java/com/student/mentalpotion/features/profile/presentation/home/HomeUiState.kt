package com.student.mentalpotion.features.profile.presentation.home

data class HomeUiState(
    val selectedTab: HomeTab = HomeTab.Overall,
    val selectedTool: Int? = null
)

enum class HomeTab {
    Overall,
    Daily
}