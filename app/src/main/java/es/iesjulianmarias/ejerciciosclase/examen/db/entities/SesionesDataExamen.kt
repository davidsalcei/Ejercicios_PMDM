package es.iesjulianmarias.ejerciciosclase.examen.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen

@Entity(
    tableName = EstructuraExamen.Sesion.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = UsuariosDataExamen::class,
        parentColumns = [EstructuraExamen.Usuario.IDUSUARIO],
        childColumns = [EstructuraExamen.Sesion.IDUSUARIO],
        onDelete = ForeignKey.CASCADE
    )]
)

data class SesionesDataExamen (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EstructuraExamen.Sesion.IDSESION) val idSesion: Int = 0,

    @ColumnInfo(name = EstructuraExamen.Sesion.IDUSUARIO) val idUsuario: Int
)