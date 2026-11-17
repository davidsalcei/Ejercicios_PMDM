package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura

// Entidad Usuario
@Entity(
    tableName = Estructura.Usuario.TABLE_NAME,
    indices = [Index(value = [Estructura.Usuario.EMAIL], unique = true)]
)

data class UsuarioData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Usuario.IDUSUARIO) val idUsuario: Int = 0,

    @ColumnInfo(name = Estructura.Usuario.NOMBRE) val nombreUsuario: String,
    @ColumnInfo(name = Estructura.Usuario.APELLIDOS) val apellidosUsuario: String,
    @ColumnInfo(name = Estructura.Usuario.EMAIL) val email: String,
    @ColumnInfo(name = Estructura.Usuario.PASSWORD) val password: String,
    @ColumnInfo(name = Estructura.Usuario.FECHA) val fecha: String
)

// Entidad Sesion
@Entity(
    tableName = Estructura.Sesion.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Estructura.Usuario::class,
        parentColumns = [Estructura.Usuario.IDUSUARIO],
        childColumns = [Estructura.Sesion.IDUSUARIO],
        onDelete = ForeignKey.CASCADE
    )]
)

data class SesionData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Sesion.IDSESION) val idSesion: Int = 0,

    @ColumnInfo(name = Estructura.Sesion.IDUSUARIO) val idUsuario: Int,
    @ColumnInfo(name = Estructura.Sesion.FECHA_INICIO) val fechaInicio: String
)

// Entidad Coche
@Entity(
    tableName = Estructura.Coche.TABLE_NAME,
    indices = [Index(value = [Estructura.Coche.TITULO], unique = true)]
)

data class CocheData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Coche.IDCOCHE) val idCoche: Int = 0,

    @ColumnInfo(name = Estructura.Coche.TITULO) val tituloCoche: String,
    @ColumnInfo(name = Estructura.Coche.DESCRIPCION) val descripcionCoche: String,
    @ColumnInfo(name = Estructura.Coche.IMAGEN) val imagenCoche: String,
    @ColumnInfo(name = Estructura.Coche.CABALLOS) val caballosCoche: String,
    @ColumnInfo(name = Estructura.Coche.CONSUMO) val consumoCoche: String,
    @ColumnInfo(name = Estructura.Coche.PRECIO) val precioCoche: String,
    @ColumnInfo(name = Estructura.Coche.URL) val urlCoche: String
)