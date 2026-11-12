package es.iesjulianmarias.ejerciciosclase.navigation

sealed class AppScreens (val route: String) {

    // Ejercicio 6 - Navegacion
    object PrimeraVentana: AppScreens("PrimeraVentana")
    object SegundaVentana: AppScreens("SegundaVentana")

    // Practica 2
    object VentanaListaCoches: AppScreens("VentanaListaCoches")
    object VentanaCoche: AppScreens("VentanaCoche")

    // Ejercicio 7
    object Inicio: AppScreens("Inicio")
    object Formulario: AppScreens("Formulario")
    object Resultados: AppScreens("Resultados")

    // Practica 3
    object FormularioCoche: AppScreens("FormularioCoche")
    object ListaCoches: AppScreens("ListaCoches")
    object SoloCoche: AppScreens("SoloCoche")
}