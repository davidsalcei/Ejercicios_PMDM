package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura

@Entity(
    tableName = Estructura.Sesion.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = UsuariosData::class,
        parentColumns = [Estructura.Usuario.IDUSUARIO],
        childColumns = [Estructura.Sesion.IDUSUARIO],
        onDelete = ForeignKey.CASCADE
    )]
)

data class SesionesData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Sesion.IDSESION) val idSesion: Int = 0,

    @ColumnInfo(name = Estructura.Sesion.IDUSUARIO) val idUsuario: Int
)