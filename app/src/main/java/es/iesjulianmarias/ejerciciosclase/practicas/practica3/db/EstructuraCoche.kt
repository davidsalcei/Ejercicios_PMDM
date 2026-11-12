package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

class EstructuraCoche {

    object DataBase {
        const val NAME = "DBCoche.db" // Nombre de la bd
    }

    object Coche {
        const val TABLE_NAME = "COCHES"
        const val IDCOCHE = "idCoche"
        const val TITULO = "titulo"
        const val DESCRIPCION = "descripcion"
        const val IMAGEN = "imagen"
        const val CABALLOS = "caballos"
        const val CONSUMO = "consumo"
        const val PRECIO = "precio"
        const val URL = "url"
    }

}