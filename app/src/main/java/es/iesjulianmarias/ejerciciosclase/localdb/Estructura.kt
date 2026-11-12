package es.iesjulianmarias.ejerciciosclase.localdb

class Estructura {
    //BASE DE DATOS
    object DB {
        const val NAME = "DBUsuarios.db" // Nombre de la bd, creación de un archivo con ese nombre
    }

    // TABLA 1: Usuario
    object Usuario {
        const val TABLE_NAME = "USUARIOS" // Nombre de la tabla

        // Nombre de los atributos/campos
        const val IDUSUARIO = "idUsuario"
        const val NOMBRE = "nombreUsuario"
        const val APELLIDOS = "apellidosUsuario"
        const val INCORPORACION = "incorporacionUsuario"
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    // TABLA 2: Sesion
    object Sesion {
        const val TABLE_NAME = "SESIONES"
        const val IDSESION = "idSesion"
        const val IDUSUARIO = "idUsuario"
        const val FECHA_INICIO = "fechaInicio"
    }

    // TABLA 3: Coches
    object Coche {
        const val TABLE_NAME = "COCHES"
        const val TITULO = "titulo"
        const val DESCRIPCION = "descripcion"
        const val IMAGEN = "imagen"
        // caballos, consumo, precio, url (wikipedia)
    }
}
