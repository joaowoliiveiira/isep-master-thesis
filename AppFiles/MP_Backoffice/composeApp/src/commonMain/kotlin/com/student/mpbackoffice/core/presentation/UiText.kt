package com.student.mpbackoffice.core.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Based on https://github.com/philipplackner/CMP-Bookpedia/blob/initial/composeApp/src/commonMain/kotlin/com/plcoding/bookpedia/core/presentation/UiText.kt
 * https://www.youtube.com/watch?v=mB1Lej0aDus
 */
sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}