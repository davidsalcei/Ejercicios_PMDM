package es.iesjulianmarias.ejerciciosclase.navigation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio6.screens.PrimeraVentana
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio6.screens.SegundaVentana
import es.iesjulianmarias.ejerciciosclase.practicas.practica2.screens.VentanaCoche
import es.iesjulianmarias.ejerciciosclase.practicas.practica2.screens.VentanaListaCoches
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio7.Formulario
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio7.Inicio
import es.iesjulianmarias.ejerciciosclase.ejercicios.ejercicio7.Resultados
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens.FormularioCoche
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens.ListaCoches
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.screens.SoloCoche

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    //val db = Room.databaseBuilder(context, AppDB::class.java, Estructura.DB.NAME).allowMainThreadQueries().build()
    //val sesionDao = db.sesionDao().getEstadoSesion()

    NavHost(
        navController = navController,
        //startDestination = if (sesionDao == null) AppScreens.Inicio.route else AppScreens.Resultados.route
        startDestination = AppScreens.VentanaListaCoches.route
    ) {
        // Ejercicio 6 - Navegacion
        composable(route = AppScreens.PrimeraVentana.route) {
            PrimeraVentana(navController)
        }
        composable(
            route = AppScreens.SegundaVentana.route + "/{text1}/{text2}",
            arguments = listOf(
                navArgument(name = "text1") {
                    type = NavType.StringType
                },
                navArgument(name = "text2") {
                    type = NavType.StringType
                }
            )
        ) {
            SegundaVentana(
                navController,
                it.arguments?.getString("text1"),
                it.arguments?.getString("text2")
            )
        }

        // Practica 2
        composable(route = AppScreens.VentanaListaCoches.route) {
            VentanaListaCoches(navController)
        }
        composable(
            route = AppScreens.VentanaCoche.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )
        ) {
            VentanaCoche(
                navController,
                it.arguments?.getInt("id")
            )
        }

        // Ejercicio 7
        composable(route = AppScreens.Inicio.route) {
            Inicio(navController)
        }
        composable(route = AppScreens.Formulario.route) {
            BackHandler(true) { } // Sirve para interceptar y manejar el evento del botón físico Atrás del dispositivo. El parámetro true indica que el sistema no ejecutará su comportamiento predeterminado (cerrar la app o regresar a la pantalla anterior).
            Formulario(navController)
        }
        composable(route = AppScreens.Resultados.route) {

            BackHandler(true) {
                // Aquí dentro podemos mandar un mensaje Toast para notificar e informar al usuario.
                Toast.makeText(context, "No se puede volver para atrás", Toast.LENGTH_SHORT).show()
            }
            Resultados(navController)
        }

        // Practica 3
        composable(route = AppScreens.FormularioCoche.route) {
            BackHandler(true) { } // Sirve para interceptar y manejar el evento del botón físico Atrás del dispositivo. El parámetro true indica que el sistema no ejecutará su comportamiento predeterminado (cerrar la app o regresar a la pantalla anterior).
            FormularioCoche(navController)
        }
        composable(route = AppScreens.ListaCoches.route) {
            BackHandler(true) { } // Sirve para interceptar y manejar el evento del botón físico Atrás del dispositivo. El parámetro true indica que el sistema no ejecutará su comportamiento predeterminado (cerrar la app o regresar a la pantalla anterior).
            ListaCoches(navController)
        }
        composable(
            route = AppScreens.SoloCoche.route + "/{titulo}",
            arguments = listOf(
                navArgument(name = "titulo") {
                    type = NavType.StringType
                }
            )
        ) {
            SoloCoche(navController, it.arguments?.getString("titulo"))
            BackHandler(true) {
                Toast.makeText(context, "No puedes volver todavía", Toast.LENGTH_SHORT).show()
            }

        }
    }
}