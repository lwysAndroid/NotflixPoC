package com.hcl.notflixpoc.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hcl.notflixpoc.presentation.features.NavigationItem
import com.hcl.notflixpoc.presentation.theme.Gray
import com.hcl.notflixpoc.presentation.theme.PrimaryColor

@Composable
fun BottomNavBar(
    allScreens: List<NavigationItem>,
    onTabSelected: (NavigationItem) -> Unit,
    currentScreen: NavigationItem,
    modifier: Modifier = Modifier,
) {

    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .alpha(0.95F),
        backgroundColor = MaterialTheme.colorScheme.surface,
        cutoutShape = RoundedCornerShape(70),
        elevation = 16.dp
    ) {

        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentColor = PrimaryColor
        ) {
            allScreens.forEach { item ->
                val isSelected = item == currentScreen

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon!!),
                            contentDescription = stringResource(id = item.title)
                        )
                    },
                    label = { Text(text = stringResource(id = item.title)) },
                    selectedContentColor = PrimaryColor,
                    unselectedContentColor = Gray,
                    alwaysShowLabel = true,
                    selected = isSelected,
                    onClick = { onTabSelected(item) }
                )
            }
        }
    }
}
