package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = EstructuraCoche.Coche.TABLE_NAME,
    indices = [Index(value = [EstructuraCoche.Coche.TITULO], unique = true)]
)

data class CocheData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = EstructuraCoche.Coche.IDCOCHE) val idUsuario: Int = 0, // Clave primaria autogenerada automáticamente

    @ColumnInfo(name = EstructuraCoche.Coche.TITULO) val tituloCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.DESCRIPCION) val descripcionCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.IMAGEN) val imagenCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.CABALLOS) val caballosCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.CONSUMO) val consumoCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.PRECIO) val precioCoche: String,
    @ColumnInfo(name = EstructuraCoche.Coche.URL) val urlCoche: String
)