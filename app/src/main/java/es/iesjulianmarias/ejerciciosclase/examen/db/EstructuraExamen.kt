package es.iesjulianmarias.ejerciciosclase.examen.db

class EstructuraExamen {

    object DB {
        const val NAME = "DBExamen.db"
    }

    object Usuario {
        const val TABLE_NAME = "USUARIOS"

        const val IDUSUARIO = "idUsuario"
        const val NOMBRE = "nombreUsuario"
        const val APELLIDOS = "apellidosUsuario"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val INCORPORACION = "incorporacion"
        const val SEXO = "sexo"
    }

    object Sesion {
        const val TABLE_NAME = "SESIONES"

        const val IDSESION = "idSesion"
        const val IDUSUARIO = "idUsuario"
    }

    object Amistad {
        const val TABLE_NAME = "AMISTADES"

        const val IDUSUARIO1 = "idUsuario1"
        const val IDUSUARIO2 = "idUsuario2"
    }

}