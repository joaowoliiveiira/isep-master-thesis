package com.student.mentalpotion.features.profile.presentation

data class ProfileUiState (
    val selectedTab: HomeTab = HomeTab.Overall,
    val selectedTool: Int? = null
)

enum class HomeTab {
    Overall,
    Daily
}