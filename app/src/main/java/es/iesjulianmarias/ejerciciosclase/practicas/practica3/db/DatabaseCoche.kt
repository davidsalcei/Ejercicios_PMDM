package es.iesjulianmarias.ejerciciosclase.practicas.practica3.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CocheData::class], version = 1, exportSchema = true
)

abstract class DatabaseCoche : RoomDatabase() {

    abstract fun cocheDao(): CocheDao

}