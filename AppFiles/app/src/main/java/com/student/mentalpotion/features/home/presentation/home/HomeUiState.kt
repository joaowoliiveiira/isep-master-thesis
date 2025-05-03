package com.student.mentalpotion.features.home.presentation.home

data class HomeUiState(
    val selectedTab: HomeTab = HomeTab.Overall,
    val selectedTool: Int? = null
)

enum class HomeTab {
    Overall,
    Daily
}