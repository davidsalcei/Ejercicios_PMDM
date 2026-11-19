package es.iesjulianmarias.ejerciciosclase.navigation

sealed class AppScreens (val route: String) {

    object LoginExamen: AppScreens("LoginExamen")
    object RegisterExamen: AppScreens("RegisterExamen")
    object PerfilUsuario: AppScreens("PerfilUsuario")
    object Amigos: AppScreens("Amigos")

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
    object LoginUsuario: AppScreens("LoginUsuario")
    object RegisterUsuario: AppScreens("RegisterUsuario")
    object ListaUsuarios: AppScreens("ListaUsuarios")
    object FormularioCoche: AppScreens("FormularioCoche")
    object ListaCoches: AppScreens("ListaCoches")
    object SoloCoche: AppScreens("SoloCoche")
}