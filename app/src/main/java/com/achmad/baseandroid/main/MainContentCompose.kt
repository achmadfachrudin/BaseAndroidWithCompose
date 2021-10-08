package com.achmad.baseandroid.main

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.achmad.baseandroid.navigation.PageDestination

@Composable
fun HomePageCompose(navController: NavController) {
    Text(
        text = "this is home screen"
    )
}

@Composable
fun AccountPageCompose(navController: NavController) {
    Text(
        text = "this is account screen",
        Modifier.clickable {
            navController.navigate(PageDestination.ABOUT.name + "/success")
        }
    )
}

@Composable
fun AboutPageCompose(navController: NavController, wording: String?) {
    Text(text = "this is about screen with argument $wording")
}
