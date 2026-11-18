package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.Estructura

@Entity(
    tableName = Estructura.Coche.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = UsuariosData::class,
        parentColumns = [Estructura.Usuario.IDUSUARIO],
        childColumns = [Estructura.Coche.IDUSUARIO],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = [Estructura.Coche.TITULO], unique = true)]
)

data class CochesData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Estructura.Coche.IDCOCHE) val idCoche: Int = 0,

    @ColumnInfo(name = Estructura.Coche.TITULO) val tituloCoche: String,
    @ColumnInfo(name = Estructura.Coche.DESCRIPCION) val descripcionCoche: String,
    @ColumnInfo(name = Estructura.Coche.IMAGEN) val imagenCoche: String,
    @ColumnInfo(name = Estructura.Coche.CABALLOS) val caballosCoche: String,
    @ColumnInfo(name = Estructura.Coche.CONSUMO) val consumoCoche: String,
    @ColumnInfo(name = Estructura.Coche.PRECIO) val precioCoche: String,
    @ColumnInfo(name = Estructura.Coche.URL) val urlCoche: String,
    @ColumnInfo(name = Estructura.Coche.IDUSUARIO) val idUsuario: Int,
)