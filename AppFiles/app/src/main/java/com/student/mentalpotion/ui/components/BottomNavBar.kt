package com.student.mentalpotion.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.student.mentalpotion.core.navigation.AppDestinations
import com.student.mentalpotion.ui.theme.BackgroundDark
import com.student.mentalpotion.ui.theme.GoldenBorder

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (AppDestinations) -> Unit
) {
    // Backstack entry to get the current Route (in state form) so we can highlight it in the bar
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = modifier,
        containerColor = BackgroundDark,
        tonalElevation = 5.dp
    ) {
        // Set each item in the bar
        AppDestinations.bottomBarItems.forEach { item ->

            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GoldenBorder,
                    unselectedIconColor = Color.Gray
                ),
                icon = { item.icon?.let { Icon(it, contentDescription = item.label) } },
                label = { Text(item.label.toString()) }
            )
        }
    }
}