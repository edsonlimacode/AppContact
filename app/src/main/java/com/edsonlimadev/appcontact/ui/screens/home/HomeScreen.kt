package com.edsonlimadev.appcontact.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.edsonlimadev.appcontact.ui.navigation.host.HomeNavHost


@Composable
fun HomeScreen(

    onLogout: () -> Unit,
) {
    HomeNavHost(
        onLogout = onLogout
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onLogout = {}
    )
}