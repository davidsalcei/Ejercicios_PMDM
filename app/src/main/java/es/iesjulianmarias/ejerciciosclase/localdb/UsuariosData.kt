package es.iesjulianmarias.ejerciciosclase.localdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

//Tabla Usuario
@Entity(
    tableName = Estructura.Usuario.TABLE_NAME,
    indices = [Index(value = [Estructura.Usuario.EMAIL], unique = true)]
) // Marca la clase como una entidad que corresponde a una tabla en la BD, accediendo por el nombre. Se indica que email será un campo único, creando el índice.

data class UsuarioData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Estructura.Usuario.IDUSUARIO) val idUsuario: Int = 0, // Clave primaria autogenerada automáticamente, generando valores únicos incrementales. El indicar que toma el valor 0 permite que cuando se cree un objeto, no sea necesario asignar manualmente un valor.

    @ColumnInfo(name = Estructura.Usuario.NOMBRE) val nombreUsuario: String, // Columna nombre del usuario, mapeado a través de @ColumnInfo a una columna específica en la tabla.
    @ColumnInfo(name = Estructura.Usuario.APELLIDOS) val apellidosUsuario: String, // Apellidos del usuario, también mapeado a una columna específica.
    @ColumnInfo(name = Estructura.Usuario.INCORPORACION) val incorporacionUsuario: String, // Fecha, se almacena el valor que recibe en la columna cuyo nombre es INCORPORACIÓN.
    @ColumnInfo(name = Estructura.Usuario.EMAIL) val email: String, // Email.
    @ColumnInfo(name = Estructura.Usuario.PASSWORD) val password: String // Password almacenada en local.
)

@Entity(
    tableName = Estructura.Sesion.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = UsuarioData::class,
        parentColumns = [Estructura.Usuario.IDUSUARIO],
        childColumns = [Estructura.Sesion.IDUSUARIO],
        onDelete = ForeignKey.CASCADE
    )]
)

data class SesionData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = Estructura.Sesion.IDSESION) val idSesion: Int = 0,

    @ColumnInfo(name = Estructura.Sesion.IDUSUARIO) val idUsuario: Int,
    @ColumnInfo(name = Estructura.Sesion.FECHA_INICIO) val fechaInicio: String
)