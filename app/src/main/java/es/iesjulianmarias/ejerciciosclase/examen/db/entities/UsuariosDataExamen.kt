package es.iesjulianmarias.ejerciciosclase.examen.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.examen.db.EstructuraExamen

@Entity(
    tableName = EstructuraExamen.Usuario.TABLE_NAME,
    indices = [Index(value = [EstructuraExamen.Usuario.EMAIL], unique = true)]
)

data class UsuariosDataExamen (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EstructuraExamen.Usuario.IDUSUARIO) val idUsuario: Int = 0,

    @ColumnInfo(name = EstructuraExamen.Usuario.NOMBRE) val nombreUsuario: String,
    @ColumnInfo(name = EstructuraExamen.Usuario.APELLIDOS) val apellidosUsuario: String,
    @ColumnInfo(name = EstructuraExamen.Usuario.EMAIL) val email: String,
    @ColumnInfo(name = EstructuraExamen.Usuario.PASSWORD) val password: String,
    @ColumnInfo(name = EstructuraExamen.Usuario.INCORPORACION) val incorporacion: String,
    @ColumnInfo(name = EstructuraExamen.Usuario.SEXO) val sexo: String?
)