package com.achmad.baseandroid.main

import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.achmad.baseandroid.BuildConfig
import com.achmad.baseandroid.R
import com.achmad.baseandroid.navigation.PageDestination
import com.achmad.baseandroid.splitinstall.LoadFeature
import com.achmad.baseandroid.theme.component.BaseToolbar

@Composable
fun MainPageCompose() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationItem.HOME,
        BottomNavigationItem.ACCOUNT,
        BottomNavigationItem.POKEMON
    )

    Scaffold(
        topBar = {
            BaseToolbar(
                title = "Base Android",
                showLeftButton = false
            )
        },
        bottomBar = {
            MainBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        MainContentScreen(navController)
    }
}

@Composable
private fun MainContentScreen(
    navController: NavHostController,
    startDestination: PageDestination = PageDestination.HOME
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = startDestination.name) {

        composable(PageDestination.HOME.name) {
            HomePageCompose(navController)
        }

        composable(PageDestination.ACCOUNT.name) {
            AccountPageCompose(navController)
        }

        composable(PageDestination.ABOUT.name + "/{userId}") { backStackEntry ->
            AboutPageCompose(navController, backStackEntry.arguments?.getString("userId"))
        }

        dialog(PageDestination.LIST.name) {
            LoadFeature(
                context = context,
                featureName = ":feature_pokemon",
                onDismiss = {
                    navController.navigateUp()
                }
            ) {
                val intent = Intent()
                intent.setClassName(
                    BuildConfig.APPLICATION_ID,
                    "com.achmad.feature.pokemon.PokemonListActivity"
                )
                context.startActivity(intent)
            }
        }
    }
}

@Composable
private fun MainBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationItem>
) {
    BottomNavigation {
        items.forEach { menu ->
            val prevRoute = navController.currentBackStackEntry?.destination?.route

            BottomNavigationItem(
                icon = {
                    Icon(menu.icon, "")
                },
                label = {
                    Text(text = stringResource(id = menu.label))
                },
                selected = prevRoute != menu.route.name,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(menu.route.name) {
                        if (prevRoute != menu.route.name) {
                            popUpTo(PageDestination.HOME.name)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

sealed class BottomNavigationItem(
    val route: PageDestination,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object HOME : BottomNavigationItem(
        PageDestination.HOME,
        R.string.title_feature_home,
        Icons.Filled.Home
    )

    object ACCOUNT :
        BottomNavigationItem(
            PageDestination.ACCOUNT,
            R.string.title_feature_account,
            Icons.Filled.Face
        )

    object POKEMON :
        BottomNavigationItem(
            PageDestination.LIST,
            R.string.title_feature_pokemon,
            Icons.Filled.Person
        )
}
