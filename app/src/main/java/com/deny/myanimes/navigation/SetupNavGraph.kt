package com.deny.myanimes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.deny.myanimes.views.DetailsAnime
import com.deny.myanimes.views.HomeView

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screens.home.route){
        composable(
            route = Screens.home.route
        ){
            HomeView(navController = navController)
        }
        composable(
            route = Screens.details.route,
            arguments = listOf(
                navArgument(name = DETAILS_ARGUMENT_KEY1){
                    type = NavType.StringType
                    defaultValue = "carregando"
                }, navArgument(name = DETAILS_ARGUMENT_KEY2){
                    type = NavType.StringType
                    defaultValue = "carregando"
                },navArgument(name = DETAILS_ARGUMENT_KEY3){
                    type = NavType.StringType
                    defaultValue = "carregando"
                },navArgument(name = DETAILS_ARGUMENT_KEY4){
                    type = NavType.StringType
                }
            )
        ){
            DetailsAnime(navController = navController, it.arguments)
        }
    }
}