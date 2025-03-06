package com.edsonlimadev.appcontact.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.edsonlimadev.appcontact.R
import com.edsonlimadev.appcontact.ui.theme.Dark800
import com.edsonlimadev.appcontact.ui.theme.Gray500
import com.edsonlimadev.appcontact.ui.theme.Violet500


sealed class NavigationBarItem(
    val label: String,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int
) {
    data object Home : NavigationBarItem(
        label = "Contatos",
        icon = R.drawable.ic_contacts,
        iconSelected = R.drawable.ic_contacts_fill
    )

    data object Favorite : NavigationBarItem(
        label = "Favoritos",
        icon = R.drawable.ic_favorite,
        iconSelected = R.drawable.ic_favorite_fill
    )

    data object Profile : NavigationBarItem(
        label = "Perfil",
        icon = R.drawable.ic_profile,
        iconSelected = R.drawable.ic_profile_fill
    )
}

private val items = listOf(
    NavigationBarItem.Home,
    NavigationBarItem.Favorite,
    NavigationBarItem.Profile
)

@Composable
fun BottomNavigationBar(
    selectedItem: NavigationBarItem = NavigationBarItem.Home,
    onClickNavigate: (NavigationBarItem) -> Unit
) {

    NavigationBar(modifier = Modifier, containerColor = Dark800) {

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(if (selectedItem == item) item.iconSelected else item.icon),

                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                selected = selectedItem == item,
                onClick = {
                    onClickNavigate(item)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Violet500,
                    unselectedIconColor = Gray500,
                    selectedTextColor = Violet500,
                    unselectedTextColor = Gray500,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
