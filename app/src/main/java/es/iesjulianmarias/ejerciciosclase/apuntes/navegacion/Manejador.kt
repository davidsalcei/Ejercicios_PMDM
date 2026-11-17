package es.iesjulianmarias.ejerciciosclase.apuntes.navegacion

sealed class Manejador(val ruta: String) {

    object Primera: Manejador("Primera")
    object Segunda: Manejador("Segunda")

}