package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

class Estructura {

    object DataBase {
        const val NAME = "DBCoche.db" // Nombre de la bd
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
        const val FECHA_INICIO = "fechaInicio"
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