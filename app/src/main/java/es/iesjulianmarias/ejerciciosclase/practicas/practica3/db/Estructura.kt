package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

class Estructura {

    object DB {
        const val NAME = "DBCoches.db"
    }

    object Usuario {
        const val TABLE_NAME = "USUARIOS"

        const val IDUSUARIO = "idUsuario"
        const val NOMBRE = "nombreUsuario"
        const val APELLIDOS = "apellidosUsuario"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val FECHA = "fecha"
    }

    object Sesion {
        const val TABLE_NAME = "SESIONES"

        const val IDSESION = "idSesion"
        const val IDUSUARIO = "idUsuario"
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
        const val IDUSUARIO = "idUsuario"
    }

}