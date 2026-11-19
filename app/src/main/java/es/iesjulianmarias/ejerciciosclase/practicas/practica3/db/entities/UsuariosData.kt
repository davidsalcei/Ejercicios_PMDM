package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura

@Entity(
    tableName = Estructura.Usuario.TABLE_NAME,
    indices = [Index(value = [Estructura.Usuario.EMAIL], unique = true)]
)

data class UsuariosData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Usuario.IDUSUARIO) val idUsuario: Int = 0,

    @ColumnInfo(name = Estructura.Usuario.NOMBRE) val nombreUsuario: String,
    @ColumnInfo(name = Estructura.Usuario.APELLIDOS) val apellidosUsuario: String,
    @ColumnInfo(name = Estructura.Usuario.EMAIL) val email: String,
    @ColumnInfo(name = Estructura.Usuario.PASSWORD) val password: String,
    @ColumnInfo(name = Estructura.Usuario.FECHA) val fecha: String?
)