package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao.CocheDao
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao.SesionDao
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao.UsuarioDao
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.CochesData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.SesionesData
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.UsuariosData

@Database(
    entities = [UsuariosData::class, SesionesData::class, CochesData::class], version = 1, exportSchema = true
)

abstract class AppDB: RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao
    abstract fun SesionDao(): SesionDao
    abstract fun CocheDao(): CocheDao
}