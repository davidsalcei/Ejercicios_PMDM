package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.dao.CocheDao
import es.iesjulianmarias.ejerciciosclase.practicas.practica3.db.entities.CocheData

@Database(
    entities = [CocheData::class], version = 1, exportSchema = true
)

abstract class DatabaseCoche : RoomDatabase() {

    abstract fun cocheDao(): CocheDao

}