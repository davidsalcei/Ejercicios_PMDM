package es.iesjulianmarias.ejerciciosclase.examen.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen

@Entity(
    tableName = EstructuraExamen.Amistad.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UsuariosDataExamen::class,
            parentColumns = [EstructuraExamen.Usuario.IDUSUARIO],
            childColumns = [EstructuraExamen.Amistad.IDUSUARIO1],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UsuariosDataExamen::class,
            parentColumns = [EstructuraExamen.Usuario.IDUSUARIO],
            childColumns = [EstructuraExamen.Amistad.IDUSUARIO2],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = [EstructuraExamen.Amistad.IDUSUARIO1, EstructuraExamen.Amistad.IDUSUARIO2]
)

data class AmistadesDataExamen (
    @ColumnInfo(name = EstructuraExamen.Amistad.IDUSUARIO1) val idUsuario1: Int,
    @ColumnInfo(name = EstructuraExamen.Amistad.IDUSUARIO2) val idUsuario2: Int
)