package es.iesjulianmarias.ejerciciosclase.apuntes.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navegador() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Manejador.Primera.ruta
    ) {
        composable(route = Manejador.Primera.ruta) {
            Primera(navController)
        }

        composable(
            route = Manejador.Segunda.ruta + "/{text}/{num}",
            arguments = listOf(
                navArgument(name = "text") {
                    type = NavType.StringType
                },
                navArgument(name = "num") {
                    type = NavType.IntType
                }
            )
        ) {
            Segunda(navController, it.arguments?.getString("text"), it.arguments?.getInt("num"))
        }
    }

}